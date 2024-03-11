package com.tigerbk.project1.common.vo;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResPagingData<T> {
    private T data;
}
