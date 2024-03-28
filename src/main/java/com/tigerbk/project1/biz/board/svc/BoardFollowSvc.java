package com.tigerbk.project1.biz.board.svc;


import com.tigerbk.project1.biz.auth.UserAuthSvc;
import com.tigerbk.project1.dto.TbBoardFollowDto;
import com.tigerbk.project1.dto.TbBoardFollowIdDto;
import com.tigerbk.project1.entity.TbBoardFollow;
import com.tigerbk.project1.entity.TbBoardFollowId;
import com.tigerbk.project1.repo.TbBoardFollowRepository;
import com.tigerbk.project1.utils.Cmapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardFollowSvc {
    private final ModelMapper modelMapper;
    private final Cmapper cmapper;

    private final UserAuthSvc userAuthSvc;
    private final TbBoardFollowRepository tbBoardFollowRepository;

    // 1 Follow 하기
    public TbBoardFollow saveFollow(@Valid String custId) throws Exception {
        TbBoardFollow tbBoardFollow = modelMapper.map(TbBoardFollowDto.builder()
                .id(TbBoardFollowIdDto.builder()
                        .custId(userAuthSvc.getSessionUser().getCustId())
                        .followCustId(custId)
                        .build()
                )
                .crtDtm(LocalDateTime.now())
                .build()
                , TbBoardFollow.class);
       return tbBoardFollowRepository.save(tbBoardFollow);
    }

    // 2 Follow 취소
    public void cancleFollow(@Valid String custId) throws Exception {
       tbBoardFollowRepository.deleteById(modelMapper.map(TbBoardFollowIdDto.builder()
                .custId(userAuthSvc.getSessionUser().getCustId())
                .followCustId(custId)
                .build() , TbBoardFollowId.class));
    }
    // 3 Follow 여부 확인
    public boolean isFollow(@Valid String custId) throws Exception {
       return tbBoardFollowRepository.existsById(modelMapper.map(TbBoardFollowIdDto.builder()
                .custId(userAuthSvc.getSessionUser().getCustId())
                .followCustId(custId)
                .build() , TbBoardFollowId.class));
    }

    // 4 Follow 리스트 조회
    public Optional<TbBoardFollow> findFollow() throws Exception {
        return tbBoardFollowRepository.findById_CustId(userAuthSvc.getSessionUser().getCustId());
    }
    // 5 Follow 카운트 조회
    public Long countFollow() throws Exception {
        return tbBoardFollowRepository.countById_CustId(userAuthSvc.getSessionUser().getCustId());
    }
}
