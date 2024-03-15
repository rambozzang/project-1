package com.tigerbk.project1.biz.cust;


import com.tigerbk.project1.entity.TbCustMaster;

import com.tigerbk.project1.exception.DefaultException;
import com.tigerbk.project1.exception.NotFoundException;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import com.tigerbk.project1.security.JwtUtil;
import com.tigerbk.project1.utils.Cmapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustSvc {

    private final TbCustMasterRepository custRepo;
    private final JwtUtil jwtUtil;
    private final Cmapper cmapper;


    // 1. 패스워드를 통한 로그인 서비스
//    public CustVO.InfoOutVo LoginbyPasswd(String custId , String passwd){
//        return custRepo.
//    }


    // 2. Pin번호를 통한 로그인
    public CustVO.InfoOutVo LoginByPinPasswd(@Valid String custId, String pinPasswd) throws Exception {

        TbCustMaster tbCustMaster = custRepo.findByCustIdAndPinPasswd(custId, pinPasswd)
                .orElseThrow(() -> new DefaultException("비밀번호를 다시 입력해주세요!"));
        CustVO.InfoOutVo outvo = cmapper.run(tbCustMaster, CustVO.InfoOutVo.class);
        outvo.setFcmId(jwtUtil.getAccessToken(custId));

        return outvo;
    }

    public CustVO.InfoOutVo LoginByBio(@Valid String custId) throws Exception {

        TbCustMaster tbCustMaster = custRepo.findByCustId(custId)
                .orElseThrow(() -> new DefaultException("Bio 정보를 다시 입력해주세요!"));
        CustVO.InfoOutVo outvo = cmapper.run(tbCustMaster, CustVO.InfoOutVo.class);
        outvo.setFcmId(jwtUtil.getAccessToken(custId));

        return outvo;
    }

    public CustVO.InfoOutVo LoginByPattern(@Valid String custId, String patternPwd) throws Exception {

        TbCustMaster tbCustMaster = custRepo.findByCustIdAndPattenPasswd(custId, patternPwd)
                .orElseThrow(() -> new DefaultException("패턴번호를 다시 확인해주세요!"));
        CustVO.InfoOutVo outvo = cmapper.run(tbCustMaster, CustVO.InfoOutVo.class);
        outvo.setFcmId(jwtUtil.getAccessToken(custId));

        return outvo;
    }


    // 3. 바이오 인증을 통한 로그인
    // public Optional
    // 4. 사용자 정보 수정

}
