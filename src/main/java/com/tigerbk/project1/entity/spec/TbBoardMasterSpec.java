package com.tigerbk.project1.entity.spec;

import com.tigerbk.project1.entity.TbBoardMaster;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TbBoardMasterSpec {

    public static Specification<TbBoardMaster> equalId(Long id) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<TbBoardMaster> equalTypeCd(String typeCd) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("typeCd"), typeCd);
    }

    public static Specification<TbBoardMaster> equalTypeDtCd(String typeDtCd) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("typeDtCd"), typeDtCd);
    }

    public static Specification<TbBoardMaster> equalDepthNo(String depthNo) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("depthNo"), depthNo);
    }

    public static Specification<TbBoardMaster> equalParentId(Long parentId) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("parentId"), parentId);
    }

    public static Specification<TbBoardMaster> equalSortNo(Integer sortNo) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("sortNo"), sortNo);
    }

    public static Specification<TbBoardMaster> equalDelYn(String delYn) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("delYn"), delYn);
    }
}
