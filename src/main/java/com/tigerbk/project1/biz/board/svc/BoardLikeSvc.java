package com.tigerbk.project1.biz.board.svc;

import com.tigerbk.project1.biz.auth.UserAuthSvc;
import com.tigerbk.project1.dto.TbBoardLikeDto;
import com.tigerbk.project1.dto.TbBoardLikeIdDto;
import com.tigerbk.project1.entity.TbBoardLike;
import com.tigerbk.project1.entity.TbBoardLikeId;
import com.tigerbk.project1.exception.DefaultException;
import com.tigerbk.project1.repo.TbBoardLikeRepository;
import com.tigerbk.project1.repo.TbBoardMasterRepository;
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
public class BoardLikeSvc {
    private final ModelMapper modelMapper;
    private final Cmapper cmapper;

    private final UserAuthSvc userAuthSvc;
    private final TbBoardMasterRepository tbBoardMasterRepository;
    private final TbBoardLikeRepository tbBoardLikeRepository;


    // 1 Like 하기
    public TbBoardLike saveLike(@Valid Long boardId) throws Exception {
        TbBoardLike tbBoardLike = modelMapper.map(TbBoardLikeDto.builder()
                        .id(TbBoardLikeIdDto.builder()
                                .custId(userAuthSvc.getSessionUser().getCustId())
                                .boardId(boardId)
                                .build()
                        )
                        .crtDtm(LocalDateTime.now())
                        .build()
                , TbBoardLike.class);
        return tbBoardLikeRepository.save(tbBoardLike);
    }

    // 2 Like 취소
    public void cancleLike(@Valid Long boardId) throws Exception {
        tbBoardLikeRepository.deleteById(modelMapper.map(TbBoardLikeIdDto.builder()
                .custId(userAuthSvc.getSessionUser().getCustId())
                .boardId(boardId)
                .build() , TbBoardLikeId.class));
    }
    // 3 Like 여부 확인
    public boolean isLike(@Valid Long boardId) throws Exception {
        return tbBoardLikeRepository.existsById(modelMapper.map(TbBoardLikeIdDto.builder()
                .custId(userAuthSvc.getSessionUser().getCustId())
                .boardId(boardId)
                .build() , TbBoardLikeId.class));
    }

    // 4 Like 리스트 조회
    public List<TbBoardLike> findLike() throws Exception {
        return tbBoardLikeRepository.findById_CustId(userAuthSvc.getSessionUser().getCustId()).orElseThrow(
                () -> new DefaultException("Like 정보가 없습니다.")
        );
    }
    // 5 Like 카운트 조회
    public Long countLike() throws Exception {
        return tbBoardLikeRepository.countById_CustId(userAuthSvc.getSessionUser().getCustId());
    }


}
