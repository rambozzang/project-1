package com.tigerbk.project1.biz.auth.sns.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleUserVo {
    private String uid;
    private String email;
    private String displayName;
    private String phoneNumber;
    private String photoURL;
}
