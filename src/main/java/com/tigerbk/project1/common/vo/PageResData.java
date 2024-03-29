package com.tigerbk.project1.common.vo;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResData {

    private PageData pageData;


    @Data
    public static class PageData {

        private int currPageNum;

        private int totalPageNum;

        private int pageSize;

        private boolean isLast;

        private long totalElements;

    }
}


