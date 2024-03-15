package com.tigerbk.project1.biz.board.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
public class BoardSvo {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class BoardInVo {

        /**
         * 게시물번호
         */
        @Schema(description = "게시물번호", example = "1")
        @NotNull(message = "게시물번호가 존재하지 않습니다. 다시 확인해 주세요.")
        private String loanNo;

        /**
         * 게시판 구분코드
         */
        @Schema(description = "게시판 구분코드", example = "NOTI")
        @Size(min = 4, max = 4, message = "게시판 구분코드는 4자리여야 합니다.")
        private String typeCd;

        /**
         * 게시판 상세구분 코드
         */
        @Schema(description = "게시판 상세구분 코드", example = "NOTI")
        @Size(min = 4, max = 4, message = "게시판 상세구분 코드는 4자리여야 합니다.")
        private String typeDtCd;

        /**
         * 게시시작일자
         */
        @Schema(description = "게시시작일자", example = "202403010900")
        @Size(min = 12, max = 12, message = "게시시작일자는 12자리여야 합니다.")
        private String notiStAt;

        /**
         * 게시종료일자
         */
        @Schema(description = "게시종료일자", example = "202403010900")
        @Size(min = 12, max = 12, message = "게시종료일자는 12자리여야 합니다.")
        private String notiEdAt;

        /**
         * 게시주제
         */
        @Schema(description = "게시주제", example = "주제")
        @Size(max = 200, message = "게시주제는 200자리 이내여야 합니다.")
        private String subject;

        /**
         * 게시내용
         */
        @Schema(description = "게시내용", example = "내용")
        private String contents;

        /**
         * 게시물 계층
         */
        @Schema(description = "게시물 계층", example = "0")
        @PositiveOrZero(message = "게시물 계층은 0보다 작을수 없습니다.")
        private String depthNo;

        /**
         * 부모 게시글ID
         */
        @Schema(description = "부모 게시글ID", example = "12121312")
        @PositiveOrZero(message = "부모 게시글ID는 0보다 작을수 없습니다.")
        private String parentId;

        /**
         * 게시글 순서
         */
        @Schema(description = "게시글 순서", example = "내용")
        @PositiveOrZero(message = "게시글 순서는 0보다 작을수 없습니다.")
        private String sortNo;

        /**
         * 게시물 삭제 여부
         */
        @Schema(description = "게시물 삭제 여부", example = "N")
        @Size(min = 1, max = 1, message = "게시물 삭제 여부는 1자리여야 합니다.")
        private String delYn;


    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class BoardOutVo {

        /**
         * 게시물번호
         */
        @Schema(description = "게시물번호", example = "1")
        @NotNull(message = "게시물번호가 존재하지 않습니다. 다시 확인해 주세요.")
        private String loanNo;

        /**
         * 게시판 구분코드
         */
        @Schema(description = "게시판 구분코드", example = "NOTI")
        @Size(min = 4, max = 4, message = "게시판 구분코드는 4자리여야 합니다.")
        private String typeCd;

        /**
         * 게시판 상세구분 코드
         */
        @Schema(description = "게시판 상세구분 코드", example = "NOTI")
        @Size(min = 4, max = 4, message = "게시판 상세구분 코드는 4자리여야 합니다.")
        private String typeDtCd;

        /**
         * 게시 시작일자
         */
        @Schema(description = "게시 시작일자", example = "202403010900")
        @Size(min = 12, max = 12, message = "게시 시작일자는 12자리여야 합니다.")
        private String notiStAt;

        /**
         * 게시종료일자
         */
        @Schema(description = "게시종료일자", example = "202403010900")
        @Size(min = 12, max = 12, message = "게시종료일자는 12자리여야 합니다.")
        private String notiEdAt;

        /**
         * 게시물주제
         */
        @Schema(description = "게시물주제", example = "202403010900")
        @Size(max = 200, message = "게시물주제는 200자리 이내여야 합니다.")
        private String subject;

        /**
         * 게시내용
         */
        @Schema(description = "게시내용", example = "내용")
        private String contents;

        /**
         * 게시물 계층
         */
        @Schema(description = "게시물 계층", example = "0")
        @PositiveOrZero(message = "게시물 계층은 0보다 작을수 없습니다.")
        private String depthNo;

        /**
         * 부모 게시글ID
         */
        @Schema(description = "부모 게시글ID", example = "1212312")
        @PositiveOrZero(message = "부모 게시글ID는 0보다 작을수 없습니다.")
        private String parentId;

        /**
         * 게시글 순서
         */
        @Schema(description = "게시글 순서", example = "내용")
        @PositiveOrZero(message = "게시글 순서는 0보다 작을수 없습니다.")
        private String sortNo;

        /**
         * 게시물 삭제 여부
         */
        @Schema(description = "게시물 삭제 여부", example = "N")
        @Size(min = 1, max = 1, message = "게시물 삭제 여부는 1자리여야 합니다.")
        private String delYn;

    }
}
