package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbBoardFollow;
import com.tigerbk.project1.entity.TbBoardFollowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TbBoardFollowRepository extends JpaRepository<TbBoardFollow, TbBoardFollowId> {

//    List<TbBoardFollow> findByCustId(String custId);
    Optional<TbBoardFollow> findById(TbBoardFollowId  tbBoardFollowId );
    Optional<TbBoardFollow> findById_CustId(String custId );

    Long countById_CustId(String custId);



}