package com.tigerbk.project1.biz.board.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BoardCvo {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class BoardInVo {

        /**
         * 페이지 Num
         */
        @Schema(description = "현재페이지", example = "0")
        private String pageNum;

        /**
         * 페이지 Size
         */
        @Schema(description = "페이지에 보여지는 데이터수", example = "20")
        private String pageSize;

        /**
         * 게시물번호
         */
        @Schema(description = "게시물번호", example = "1")
        @NotNull(message = "게시물번호가 존재하지 않습니다. 다시 확인해 주세요.")
        private String id;
    }
}
