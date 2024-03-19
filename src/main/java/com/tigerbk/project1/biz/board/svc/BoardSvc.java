package com.tigerbk.project1.biz.board.svc;

import com.tigerbk.project1.biz.board.vo.BoardSvo;
import com.tigerbk.project1.entity.TbBoardMaster;
import com.tigerbk.project1.repo.TbBoardMasterRepository;
import com.tigerbk.project1.utils.Cmapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardSvc {
    private final ModelMapper modelMapper;

    private final TbBoardMasterRepository tbBoardMasterRepository;

    private final Cmapper cmapper;


    @Transactional
    public List<BoardSvo.BoardOutVo> findAllBoardList(@Valid BoardSvo.BoardInVo inVo) throws Exception {
       Pageable pageable = PageRequest.of(inVo.getPageNum(), inVo.getPageSize(),
                Sort.by(Sort.Direction.ASC, "crtDtm")
                        .and(Sort.by(Sort.Direction.ASC, "boardId")
                                .and(Sort.by(Sort.Direction.ASC, "depthNo")
                                        .and(Sort.by(Sort.Direction.ASC, "sortNo")))));
        Specification<TbBoardMaster> spec = (root, query, criteriaBuilder) -> null;
//        boardSpec = boardSpec.and(TbBoardMasterSpec.equalParentId(inVo.getParentId()));
          Page<TbBoardMaster> boardPage = tbBoardMasterRepository.findAll(spec, pageable);
//        List<Long> boarIdList = boardPage.stream().map(TbBoardMaster::getBoardId).collect(Collectors.toList());
//        boarIdList.add(Long.valueOf(0));
//        List<TbBoardMaster> boardAllList = tbBoardMasterRepository.findAllByParentIdIn(boarIdList);
//        List<BoardSvo.BoardOutVo> boardOutVoList = new ArrayList<>();
//        boarIdList.stream().forEach(idElement -> {
//            boardOutVoList.addAll(boardAllList.stream()
//                    .filter(Element -> (idElement.equals(Element.getBoardId()) && Long.valueOf(0).equals(Element.getParentId()))
//                            || idElement.equals(Element.getParentId()))
//                    .map(Element -> cmapper.run(Element,  BoardSvo.BoardOutVo.class))
//                    .toList());
//            });
        return boardPage.stream().map(e -> cmapper.run(e, BoardSvo.BoardOutVo.class)).collect(Collectors.toList());
    }
}
