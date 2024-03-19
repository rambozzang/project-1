package com.tigerbk.project1.biz.auth.sns.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserVo {
    private Long id;
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