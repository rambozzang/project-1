package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbBoardLike;
import com.tigerbk.project1.entity.TbBoardSingo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TbBoardSingoRepository extends JpaRepository<TbBoardSingo, Long> {


    List<TbBoardSingo> findByCustId(String custId);

    List<TbBoardSingo> findByBoardId(Long boardId);

    List<TbBoardSingo> findByCustIdAndBoardId(String custId, Long boardId);

    void deleteByCustIdAndBoardId(String custId, Long boardId);

    boolean existsByCustIdAndBoardId(String custId, Long boardId);

    Long countByCustId(String custId);
    Long countByBoardId(Long boardId);
}