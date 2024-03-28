package com.tigerbk.project1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_BOARD_SINGO")
public class TbBoardSingo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "BOARD_ID", nullable = false)
    private Long boardId;

    @Size(max = 100)
    @NotNull
    @Column(name = "CUST_ID", nullable = false, length = 100)
    private String custId;

    @Lob
    @Column(name = "SINGO_REASON")
    private String singoReason;

    @NotNull
    @Column(name = "CRT_DTM", nullable = false)
    private LocalDateTime crtDtm;

    @Column(name = "CHG_CTM")
    private LocalDateTime chgCtm;

    @Size(max = 100)
    @Column(name = "CHG_CUST_ID", length = 100)
    private String chgCustId;

}