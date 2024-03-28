package com.tigerbk.project1.biz.board.svc;

import com.tigerbk.project1.biz.auth.UserAuthSvc;
import com.tigerbk.project1.dto.TbBoardFollowDto;
import com.tigerbk.project1.dto.TbBoardFollowIdDto;
import com.tigerbk.project1.dto.TbBoardSingoDto;
import com.tigerbk.project1.entity.TbBoardSingo;
import com.tigerbk.project1.repo.TbBoardMasterRepository;
import com.tigerbk.project1.repo.TbBoardSingoRepository;
import com.tigerbk.project1.utils.Cmapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardSingoSvc {
    private final ModelMapper modelMapper;
    private final Cmapper cmapper;

    private final UserAuthSvc userAuthSvc;
    private final TbBoardMasterRepository tbBoardMasterRepository;
    private final TbBoardSingoRepository tbBoardSingoRepository;


    // 1 Singo 하기
    public TbBoardSingo saveSingo(@Valid Long boardId) throws Exception {
        TbBoardSingo tbBoardFollow = modelMapper.map(TbBoardSingoDto.builder()
                        .custId(userAuthSvc.getSessionUser().getCustId())
                        .boardId(boardId)
                        .crtDtm(LocalDateTime.now())
                        .build()
                , TbBoardSingo.class);
        return tbBoardSingoRepository.save(tbBoardFollow);
    }

    // 2 Singo 취소
    public void cancleSingo(@Valid Long boardId) throws Exception {
        tbBoardSingoRepository.deleteByCustIdAndBoardId(userAuthSvc.getSessionUser().getCustId() , boardId);
    }
    // 3 Singo 여부 확인
    public boolean isSingo(@Valid Long boardId) throws Exception {
        return tbBoardSingoRepository.existsByCustIdAndBoardId(userAuthSvc.getSessionUser().getCustId() , boardId);
    }

    // 4 Singo 리스트 조회
    public List<TbBoardSingo> findSingobyCustId() throws Exception {
        return tbBoardSingoRepository.findByCustId(userAuthSvc.getSessionUser().getCustId());
    }
    public List<TbBoardSingo> findSingobyCustIdAndBoardId(Long boardId) throws Exception {
        return tbBoardSingoRepository.findByCustIdAndBoardId(userAuthSvc.getSessionUser().getCustId() , boardId);
    }
    public List<TbBoardSingo> findSingobyBoardId(Long boardId) throws Exception {
        return tbBoardSingoRepository.findByBoardId(boardId);
    }
    // 5 Singo 카운트 조회
    public Long countSingo() throws Exception {
        return tbBoardSingoRepository.countByCustId(userAuthSvc.getSessionUser().getCustId());
    }
    // 5 Follow 카운트 조회
    public Long countSingobyBoardId(Long boardId) throws Exception {
        return tbBoardSingoRepository.countByBoardId(boardId);
    }

}
