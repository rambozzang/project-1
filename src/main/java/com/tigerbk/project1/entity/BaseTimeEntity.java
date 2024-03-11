package com.tigerbk.project1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    
    @Schema(description = "입력시간", nullable = false, example = "입력시간")
    @CreatedDate
    private LocalDateTime regDate;

    @Schema(description = "수정시간", nullable = false, example = "수정시간")
    @LastModifiedDate
    private LocalDateTime modDate;

}
