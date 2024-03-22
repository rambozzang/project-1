package com.tigerbk.project1.biz.cust.svc;


import com.tigerbk.project1.biz.cust.vo.CustVo;
import com.tigerbk.project1.entity.TbCustMaster;

import com.tigerbk.project1.exception.DefaultException;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import com.tigerbk.project1.security.JwtUtil;
import com.tigerbk.project1.utils.Cmapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustSvc {

    private final TbCustMasterRepository custRepo;
    private final JwtUtil jwtUtil;
    private final Cmapper cmapper;


    // 1. 패스워드를 통한 로그인 서비스
//    public CustVo.InfoOutVo LoginbyPasswd(String custId , String passwd){
//        return custRepo.
//    }

    public CustVo.InfoOutVo login(String custId , String fcmId) throws Exception {
        TbCustMaster tbCustMaster = custRepo.findByCustId(custId)
                .orElseThrow(() -> new DefaultException("회원 정보가 없습니다. 다시 입력해주세요!"));
        CustVo.InfoOutVo outvo = cmapper.run(tbCustMaster, CustVo.InfoOutVo.class);
        outvo.setAccessToken(jwtUtil.getAccessToken(custId));
        if(!"".equals(outvo.getFcmId())){
            tbCustMaster.setFcmId(fcmId);
            custRepo.save(tbCustMaster);
        }
        return outvo;

    }


    // 2. Pin번호를 통한 로그인
    public CustVo.InfoOutVo LoginByPinPasswd(@Valid String custId, String pinPasswd) throws Exception {

        TbCustMaster tbCustMaster = custRepo.findByCustIdAndPinPasswd(custId, pinPasswd)
                .orElseThrow(() -> new DefaultException("비밀번호를 다시 입력해주세요!"));
        CustVo.InfoOutVo outvo = cmapper.run(tbCustMaster, CustVo.InfoOutVo.class);
        outvo.setAccessToken(jwtUtil.getAccessToken(custId));

        return outvo;
    }

    public CustVo.InfoOutVo LoginByBio(@Valid String custId) throws Exception {

        TbCustMaster tbCustMaster = custRepo.findByCustId(custId)
                .orElseThrow(() -> new DefaultException("Bio 정보를 다시 입력해주세요!"));
        CustVo.InfoOutVo outvo = cmapper.run(tbCustMaster, CustVo.InfoOutVo.class);
        outvo.setAccessToken(jwtUtil.getAccessToken(custId));

        return outvo;
    }

    public CustVo.InfoOutVo LoginByPattern(@Valid String custId, String patternPwd) throws Exception {

        TbCustMaster tbCustMaster = custRepo.findByCustIdAndPattenPasswd(custId, patternPwd)
                .orElseThrow(() -> new DefaultException("패턴번호를 다시 확인해주세요!"));
        CustVo.InfoOutVo outvo = cmapper.run(tbCustMaster, CustVo.InfoOutVo.class);
        outvo.setAccessToken(jwtUtil.getAccessToken(custId));

        return outvo;
    }


    // 3. 바이오 인증을 통한 로그인
    // public Optional
    // 4. 사용자 정보 수정

}
