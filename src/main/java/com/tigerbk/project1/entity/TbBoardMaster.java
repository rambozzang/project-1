package com.tigerbk.project1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "TB_BOARD_MASTER", schema = "db_1")
public class TbBoardMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID", nullable = false)
    private Long id;

    @Size(max = 4)
    @Column(name = "TYPE_CD", length = 4)
    private String typeCd;

    @Size(max = 4)
    @Column(name = "TYPE_DT_CD", length = 4)
    private String typeDtCd;

    @Size(max = 12)
    @Column(name = "NOTI_ST_AT", length = 12)
    private String notiStAt;

    @Size(max = 12)
    @Column(name = "NOTI_ED_AT", length = 12)
    private String notiEdAt;

    @Size(max = 200)
    @Column(name = "SUBJECT", length = 200)
    private String subject;

    @Lob
    @Column(name = "CONTENTS")
    private String contents;

    @Column(name = "DEPTH_NO")
    private Integer depthNo;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "SORT_NO")
    private Integer sortNo;

    @Size(max = 1)
    @Column(name = "DEL_YN", length = 1)
    private String delYn;

    @Column(name = "CRT_DTM")
    private Instant crtDtm;

    @Size(max = 100)
    @Column(name = "CRT_CUST_ID", length = 100)
    private String crtCustId;

    @Column(name = "CHG_DTM")
    private Instant chgDtm;

    @Size(max = 100)
    @Column(name = "CHG_CUST_ID", length = 100)
    private String chgCustId;

}