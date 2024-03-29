package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbBoardMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TbBoardMasterRepository extends JpaRepository<TbBoardMaster, Long> {

    Page<TbBoardMaster> findAll(Specification<TbBoardMaster> spec, Pageable pageable);

    Optional<TbBoardMaster> findTop1ById(Long id);

    List<TbBoardMaster> findAllByIdIn(List<Long> id);

    List<TbBoardMaster> findAllByParentIdIn(List<Long> id);
}