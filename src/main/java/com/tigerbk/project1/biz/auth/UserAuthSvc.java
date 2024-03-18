package com.tigerbk.project1.biz.auth;

import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.exception.NotFoundException;
import com.tigerbk.project1.mapper.TbCustMasterMapper;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthSvc {

    private final TbCustMasterRepository custRepo;

    @Transactional
    public UserDetails getUserInfo(String custId) {
        TbCustMasterDto tbUserMainDto =
                TbCustMasterMapper.INSTANCE.toDto(custRepo.findByCustId(custId).orElseThrow(
                        () -> new NotFoundException("고객 정보가 존재하지 않습니다!")
                ));
        return UserPrincipal.create(tbUserMainDto);
    }

    /**
     * 로그인한 사람 정보 조회
     *
     * @return : 회원번호,회원명
     * id :  회원번호 : MembNo
     * email : 사업자번호 : bizno
     * password : 회원이름 : MembNm
     */

    public UserPrincipal getSessionUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            throw new NotFoundException("JWT Session 정보가 존재하지 않습니다. 다시 로그인 해주세요!");
        }
        return (UserPrincipal) principal;
    }

    public static UserPrincipal getStaticSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            throw new NotFoundException("JWT Session 정보가 존재하지 않습니다. 다시 로그인 해주세요!");
        }
        return (UserPrincipal) principal;
    }

    public static String getUserId() {
        return getStaticSession().getCustId();
    }

    public static String getUserNm() {
        return getStaticSession().getCustNm();
    }

    public static String getSeq() {
        return getStaticSession().getSeq();
    }

}
