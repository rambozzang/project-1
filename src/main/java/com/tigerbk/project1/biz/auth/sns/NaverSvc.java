package com.tigerbk.project1.biz.auth.sns;


import com.tigerbk.project1.biz.auth.sns.vo.NaverUserVo;
import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.enums.AuthProvider;
import com.tigerbk.project1.enums.Role;
import com.tigerbk.project1.exception.DefaultException;
import com.tigerbk.project1.mapper.TbCustMasterMapper;
import com.tigerbk.project1.repo.FireBaseAuthRepo;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import com.tigerbk.project1.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;


// https://developers.naver.com/apps/#/list
@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSvc {

    private final TbCustMasterRepository userRepository;
    private final JwtUtil jwtUtil;
    private final FireBaseAuthRepo fireBaseAuthRepo;

    @Transactional
    public String SignInProc(NaverUserVo naverUserVo) {

        try {
            // firebase 로그인 처리
            HashMap<String, Object> uinfo = new HashMap<>();
            uinfo.put("id", naverUserVo.getAccount().getId());
            uinfo.put("email", naverUserVo.getAccount().getEmail());
            uinfo.put("nickname", naverUserVo.getAccount().getNickname());
            uinfo.put("picture", naverUserVo.getAccount().getProfileImage());
            uinfo.put("phoneNumber", naverUserVo.getAccount().getMobile());
            String customToken = fireBaseAuthRepo.createFirebaseCustomToken(uinfo);
            log.debug("customToken : " + customToken);
            log.debug("customToken len : " + customToken.length());
            String birthday = naverUserVo.getAccount().getBirthyear() + ""+ naverUserVo.getAccount().getBirthday();
            log.debug("birthday : " + birthday);;
            if("".equals(birthday)){
                birthday = "000000";
            } else if(birthday.length()  > 6) {
                birthday = birthday.replaceAll("-", "");
                birthday = birthday.substring(2);
            }

            if (!userRepository.existsByCustId(String.valueOf(naverUserVo.getAccount().getId()))) {
                var dto = TbCustMasterDto.builder()
                        .custId(naverUserVo.getAccount().getId())
                        .provider(AuthProvider.NAVER.name())
                        .birthday(birthday)
                        .email(naverUserVo.getAccount().getEmail())
                        .custNm(StringUtils.hasText(naverUserVo.getAccount().getName()) ? naverUserVo.getAccount().getName() : naverUserVo.getAccount().getNickname())
                        .nickNm(naverUserVo.getAccount().getNickname())
                        //      .fcmId(customToken)
                        .hpNo(naverUserVo.getAccount().getMobile())
                        .profilePath(naverUserVo.getAccount().getProfileImage())
                        .role(Role.USER.toString())
                        .build();
                userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));
            }
            String accessToken = jwtUtil.getAccessToken(naverUserVo.getAccount().getId());
            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DefaultException("SignInProc 처리중오류발생 : " + e.getMessage());
        }
    }
}
