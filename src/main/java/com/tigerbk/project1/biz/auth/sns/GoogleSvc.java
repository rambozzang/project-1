package com.tigerbk.project1.biz.auth.sns;


import com.tigerbk.project1.biz.auth.sns.vo.*;
import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.enums.AuthProvider;
import com.tigerbk.project1.enums.Role;
import com.tigerbk.project1.exception.BadRequestException;
import com.tigerbk.project1.mapper.TbCustMasterMapper;
import com.tigerbk.project1.repo.FireBaseAuthRepo;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import com.tigerbk.project1.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleSvc {
    private final TbCustMasterRepository userRepository;
    private final JwtUtil jwtUtil;
    private final WebClient webClient;
    private final FireBaseAuthRepo fireBaseAuthRepo;
    @Transactional
    public String SignInProc(GoogleUserVo googleUserVo) {
        String accessToken = jwtUtil.getAccessToken(googleUserVo.getUid());
        if (userRepository.existsByCustId(googleUserVo.getUid())) {
            return accessToken;
        }
        var dto = TbCustMasterDto.builder()
                .custId(googleUserVo.getUid())
                .provider(AuthProvider.GOOGLE.name())
                .birthday(null)
                .email(googleUserVo.getEmail())
                .custNm(googleUserVo.getDisplayName())
                .nickNm(googleUserVo.getDisplayName())
                .profilePath(googleUserVo.getPhotoURL())
                .role(Role.USER.toString())
                .build();
        userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));
        return accessToken;
    }

}
