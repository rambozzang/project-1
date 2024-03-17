package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbCustMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbCustMasterRepository extends JpaRepository<TbCustMaster, Long> {

    Optional<TbCustMaster> findByCustId(String custId);

    Optional<TbCustMaster> findByCustIdAndPattenPasswd(String custId, String patternPasswd);

    Optional<TbCustMaster> findByCustIdAndPinPasswd(String custId, String pinPasswd);

    boolean existsByCustId(String custId);

}