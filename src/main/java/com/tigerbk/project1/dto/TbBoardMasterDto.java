package com.tigerbk.project1.dto;

import com.tigerbk.project1.entity.TbBoardMaster;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbBoardMaster}
 */
@Value
public class TbBoardMasterDto implements Serializable {
    Long id;
    @Size(max = 4)
    String typeCd;
    @Size(max = 4)
    String typeDtCd;
    @Size(max = 12)
    String notiStAt;
    @Size(max = 12)
    String notiEdAt;
    @Size(max = 200)
    String subject;
    String contents;
    Integer depthNo;
    TbBoardMaster parentId;
    Integer sortNo;
    @Size(max = 1)
    String delYn;
    Instant crtDtm;
    @Size(max = 100)
    String crtCustId;
    Instant chgDtm;
    @Size(max = 100)
    String chgCustId;
    List<TbBoardMasterDto> child;
}