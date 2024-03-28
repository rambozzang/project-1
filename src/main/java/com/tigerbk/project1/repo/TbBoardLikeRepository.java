package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbBoardFollow;
import com.tigerbk.project1.entity.TbBoardLike;
import com.tigerbk.project1.entity.TbBoardLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TbBoardLikeRepository extends JpaRepository<TbBoardLike, TbBoardLikeId> {


    Optional<List<TbBoardLike>> findById_CustId(String custId);
    Long countById_CustId(String custId);
}