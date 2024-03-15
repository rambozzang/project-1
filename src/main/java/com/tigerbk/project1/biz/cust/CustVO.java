package com.tigerbk.project1.biz.cust;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

public class CustVO {

    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoOutVo {

        /**  nickNm  */
        @Schema(description = "fcmId", example = "1")
        private String fcmId;

        /**  custId  */
        @Schema(description = "seq", example = "1")
        private String custId;

        /**  membNm  */
        @Schema(description = "membNm", example = "1")
        private String custNm;

        /**  nickNm  */
        @Schema(description = "nickNm", example = "1")
        private String nickNm;
        /**  nickNm  */
        @Schema(description = "email", example = "1")
        private String email;
        /**  nickNm  */
        @Schema(description = "hpNo", example = "1")
        private String hpNo;
        /**  nickNm  */
        @Schema(description = "profilePath", example = "1")
        private String profilePath;

        /**  nickNm  */
        @Schema(description = "provider", example = "1")
        private String provider;

    }
}
