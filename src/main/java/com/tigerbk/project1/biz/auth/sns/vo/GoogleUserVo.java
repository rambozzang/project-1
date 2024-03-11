package com.tigerbk.project1.biz.auth.sns.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleUserVo {
    private String id;
    private String email;
    private String name;
    private String picture;
}
