package com.space.repository;

import com.space.entities.Ship;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipsRepository extends PagingAndSortingRepository<Ship, Long>, JpaSpecificationExecutor<Ship> {

}
