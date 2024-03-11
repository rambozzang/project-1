package com.tigerbk.project1.common.vo;

import lombok.*;
import org.springframework.http.ResponseEntity;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResData<T> {
    private String code;
    private String msg;
    private T data;

    public static ResponseEntity<?> SUCCESS(Object data){
        return ResponseEntity.ok().body(ResData.builder().code(CommCont.RESULT_CODE_SUCCESS).data(data).build());
    }

    public static ResponseEntity<?> SUCCESS(Object data, String msg) {
        return ResponseEntity.ok().body(ResData.builder().code(CommCont.RESULT_CODE_SUCCESS).data(data).msg(msg).build());
    }

    public static ResponseEntity<?> FAIL(String msg){
        return ResponseEntity.ok().body(ResData.builder().code(CommCont.RESULT_CODE_FAIL).msg(msg).build());
    }
    public static ResponseEntity<?> FAIL(Object data, String msg){
        return ResponseEntity.ok().body(ResData.builder().code(CommCont.RESULT_CODE_FAIL).data(data).msg(msg).build());
    }
}
