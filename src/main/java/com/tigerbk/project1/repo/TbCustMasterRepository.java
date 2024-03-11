package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbCustMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbCustMasterRepository extends JpaRepository<TbCustMaster, Long> {

    Optional<TbCustMaster> findByMembId(String custId);

    Optional<TbCustMaster> findByMembIdAndPattenPasswd(String custId, String patternPasswd);

    Optional<TbCustMaster> findByMembIdAndPinPasswd(String custId, String pinPasswd);

    boolean existsByMembId(String membId);

}