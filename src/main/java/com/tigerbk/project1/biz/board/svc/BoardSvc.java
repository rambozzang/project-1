package com.tigerbk.project1.biz.board.svc;

import com.tigerbk.project1.biz.board.vo.BoardSvo;
import com.tigerbk.project1.dto.TbBoardMasterDto;
import com.tigerbk.project1.entity.TbBoardMaster;
import com.tigerbk.project1.entity.spec.TbBoardMasterSpec;
import com.tigerbk.project1.mapper.TbBoardMasterMapper;
import com.tigerbk.project1.repo.TbBoardMasterRepository;
import com.tigerbk.project1.utils.Cmapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @package      : com.tigerbk.project1.biz.board.svc
* @name         : BoardSvc.java
* @date         : 2024-03-20 오후 1:05
* @author       : Juheon Kim
* @version      : 1.0.0
**/
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardSvc {

    private final TbBoardMasterRepository tbBoardMasterRepository;

    private final Cmapper cmapper;


    /**
    *
    * @name     : BoardSvc.findAllBoardList
    * @author   : JuHeon Kim
    * @param    :
    * @return   :
    **/
    @Transactional
    public List<BoardSvo.ParallelOutVo> findAllPallelList(@Valid BoardSvo.BoardInVo inVo) throws Exception {
        Pageable pageable = PageRequest.of(inVo.getPageNum(), inVo.getPageSize(),
                Sort.by(Sort.Direction.ASC, "crtDtm")
                        .and(Sort.by(Sort.Direction.ASC, "id")
                                .and(Sort.by(Sort.Direction.ASC, "depthNo")
                                        .and(Sort.by(Sort.Direction.ASC, "sortNo")))));
        Specification<TbBoardMaster> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(TbBoardMasterSpec.equalSortNo(Integer.valueOf(0)));
        if (!("".equals(inVo.getId()) || inVo.getId() == null)) {
            spec = spec.and(TbBoardMasterSpec.equalId(inVo.getId()));
        }
        Page<TbBoardMaster> boardPage = tbBoardMasterRepository.findAll(spec, pageable);
        List<TbBoardMasterDto> boardDto = TbBoardMasterMapper.INSTANCE.toDtoList(boardPage.getContent());
        List<BoardSvo.ParallelOutVo> boardOutVoList = new ArrayList<>();
        this.getParallelList(boardDto, boardOutVoList);
        return boardOutVoList;
    }

    /**
     *
     * @name     : BoardSvc.findAllBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Transactional
    public List<BoardSvo.HierachiOutVo> findAllHierachiList(@Valid BoardSvo.BoardInVo inVo) throws Exception {
        Pageable pageable = PageRequest.of(inVo.getPageNum(), inVo.getPageSize(),
                Sort.by(Sort.Direction.ASC, "crtDtm")
                        .and(Sort.by(Sort.Direction.ASC, "id")
                                .and(Sort.by(Sort.Direction.ASC, "depthNo")
                                        .and(Sort.by(Sort.Direction.ASC, "sortNo")))));
        Specification<TbBoardMaster> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(TbBoardMasterSpec.equalSortNo(Integer.valueOf(0)));
        if (!("".equals(inVo.getId()) || inVo.getId() == null)) {
            spec = spec.and(TbBoardMasterSpec.equalId(inVo.getId()));
        }
        Page<TbBoardMaster> boardPage = tbBoardMasterRepository.findAll(spec, pageable);
        List<TbBoardMasterDto> boardDto = TbBoardMasterMapper.INSTANCE.toDtoList(boardPage.getContent());
        List<BoardSvo.HierachiOutVo> hierachiOutVoList = new ArrayList<>();
        boardDto.stream().forEach(e -> {
            List<BoardSvo.ChildOutVo> childOutVoList = new ArrayList<>();
            this.getHierachiList(e, childOutVoList);
            BoardSvo.HierachiOutVo hierachiOutVo = cmapper.run(e, BoardSvo.HierachiOutVo.class);
            hierachiOutVo.setParentBoradId(Long.valueOf(0));
            hierachiOutVo.setChildOutVoList(childOutVoList);
            hierachiOutVoList.add(hierachiOutVo);
        });
        return hierachiOutVoList;
    }

    /**
    *
    * @name     : BoardSvc.getBoardList
    * @author   : JuHeon Kim
    * @param    :
    * @return   :
    **/
    public void getParallelList(List<TbBoardMasterDto> dtoList, List<BoardSvo.ParallelOutVo> OutVoList) {
        dtoList.stream().forEach(e -> {
            BoardSvo.ParallelOutVo outVo = cmapper.run(e, BoardSvo.ParallelOutVo.class);
            outVo.setParentBoradId(e.getParentId() != null ? e.getParentId().getId() : Long.valueOf(0));
            OutVoList.add(outVo);
            if (e.getChild().size() != 0) {
                getParallelList(e.getChild(), OutVoList);
            }
        });
    }
    
    /**
    *
    * @name     : BoardSvc.getHierachiList
    * @author   : JuHeon Kim
    * @param    : 
    * @return   :
    **/

    public void getHierachiList(TbBoardMasterDto dto, List<BoardSvo.ChildOutVo> OutVoList) {
        dto.getChild().stream().forEach(e -> {
            BoardSvo.ChildOutVo childOutVo = cmapper.run(e, BoardSvo.ChildOutVo.class);
            childOutVo.setParentBoradId(e.getParentId() != null ? e.getParentId().getId() : Long.valueOf(0));
            OutVoList.add(childOutVo);
            if (e != null) {
                getHierachiList(e, OutVoList);
            }
        });
    }
}
