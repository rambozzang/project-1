package com.tigerbk.project1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "TB_CUST_MASTER")
public class TbCustMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "CUST_ID", nullable = false, length = 100)
    private String custId;

    @Size(max = 100)
    @Column(name = "NICK_NM", length = 100)
    private String nickNm;

    @Size(max = 100)
    @Column(name = "CUST_NM", length = 100)
    private String custNm;

    @Size(max = 100)
    @Column(name = "EMAIL", length = 100)
    private String email;

    @Size(max = 100)
    @Column(name = "HP_NO", length = 100)
    private String hpNo;

    @Size(max = 6)
    @Column(name = "BIRTHDAY", length = 6)
    private String birthday;

    @Size(max = 200)
    @Column(name = "FCM_ID", length = 200)
    private String fcmId;

    @Size(max = 100)
    @Column(name = "PROVIDER", length = 100)
    private String provider;

    @Size(max = 100)
    @Column(name = "PROFILE_PATH", length = 100)
    private String profilePath;

    @Size(max = 100)
    @Column(name = "ROLE", length = 100)
    private String role;

    @Size(max = 20)
    @Column(name = "PATTEN_PASSWD", length = 20)
    private String pattenPasswd;

    @Size(max = 6)
    @Column(name = "PIN_PASSWD", length = 6)
    private String pinPasswd;


}