package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbBoardMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbBoardMasterRepository extends JpaRepository<TbBoardMaster, Long> {

    Page<TbBoardMaster> findAll(Specification<TbBoardMaster> spec, Pageable pageable);

    List<TbBoardMaster> findAllByBoardIdIn(List<Long> boardId);

    List<TbBoardMaster> findAllByParentIdIn(List<Long> parentId);
}