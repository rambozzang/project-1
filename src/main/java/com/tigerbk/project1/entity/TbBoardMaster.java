package com.tigerbk.project1.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jdk.jfr.Category;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_BOARD_MASTER", schema = "db_1")
public class TbBoardMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private TbBoardMaster parentId;

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


    @OneToMany(mappedBy = "parentId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("depthNo asc")
    @JsonManagedReference
    private List<TbBoardMaster> child = new ArrayList<>();
}