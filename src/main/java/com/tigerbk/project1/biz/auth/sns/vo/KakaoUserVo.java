package com.tigerbk.project1.biz.auth.sns.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserVo {

    @NotBlank(message = "id는 필수값입니다.")
    private Long id;
    @NotBlank(message = "KakaoAccount는 필수값입니다.")
    private KakaoAccount kakaoAccount;

    @Getter
    public static class KakaoAccount {
        private Profile profile;
        private String email;
        private String ageRange;
        private String gender;
        private String ci;
        private String birthday;
        private String birthdayType;
        private String name;
        private String phoneNumber;       
        @Getter
        public static class Profile {
            private String nickname;
            private String profileImageUrl;
            private String thumbnailImageUrl;
            
        }
    }
}