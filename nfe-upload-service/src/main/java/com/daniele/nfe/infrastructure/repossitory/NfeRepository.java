package com.daniele.nfe.infrastructure.repossitory;


import com.daniele.nfe.infrastructure.entity.NfeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NfeRepository extends PagingAndSortingRepository<NfeEntity, Long>, JpaSpecificationExecutor<NfeEntity> {

    List<NfeEntity> findAllByOrderByDhRegistroDesc();

}
