package com.tigerbk.project1.biz.cust.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class CustVo {

    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoOutVo {

        /**  custId  */
        @Schema(description = "custId", example = "1")
        private String custId;
        /**  nickNm  */
        @Schema(description = "nickNm", example = "1")
        private String nickNm;
        /**  nickNm  */
        @Schema(description = "custNm", example = "1")
        private String custNm;
        /**  nickNm  */
        @Schema(description = "email", example = "1")
        private String email;
        /**  nickNm  */
        @Schema(description = "hpNo", example = "1")
        private String hpNo;

        /**  barthday  */
        @Schema(description = "birthday", example = "1")
        private String birthday;

        /**  nickNm  */
        @Schema(description = "fcmId", example = "1")
        private String fcmId;
        /**  nickNm  */
        @Schema(description = "provider", example = "1")
        private String provider;

        /**  nickNm  */
        @Schema(description = "profilePath", example = "1")
        private String profilePath;

        /**  accssToken  */
        @Schema(description = "accessToken", example = "1")
        private String accessToken;
    }

    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class loginVo {

        /**
         * custId
         */
        @Schema(description = "custId", example = "1")
        private String custId;
        /**  nickNm  */
        @Schema(description = "fcmId", example = "1")
        private String fcmId;
    }


}
