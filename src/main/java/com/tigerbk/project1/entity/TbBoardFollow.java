package com.tigerbk.project1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_BOARD_FOLLOW")
public class TbBoardFollow {
    @EmbeddedId
    private TbBoardFollowId id;

    @NotNull
    @Column(name = "CRT_DTM", nullable = false)
    private LocalDateTime crtDtm;

}