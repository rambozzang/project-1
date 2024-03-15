package com.tigerbk.project1.entity.spec;

import com.tigerbk.project1.entity.TbBoardMaster;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TbBoardMasterSpec {

    public static Specification<TbBoardMaster> equalBoardId(BigDecimal boardId) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("boarId"), boardId);
    }

    public static Specification<TbBoardMaster> equalTypeCd(String typeCd) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("typeCd"), typeCd);
    }

    public static Specification<TbBoardMaster> equalDepthNo(String depthNo) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("depthNo"), depthNo);
    }

    public static Specification<TbBoardMaster> equalParentId(String parentId) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("parentId"), parentId);
    }

    public static Specification<TbBoardMaster> equalSortNo(String sortNo) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("sortNo"), sortNo);
    }

    public static Specification<TbBoardMaster> equalDelYn(String delYn) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("delYn"), delYn);
    }
}
