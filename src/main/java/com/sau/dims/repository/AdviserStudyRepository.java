package com.sau.dims.repository;

import com.sau.dims.model.AdviserStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviserStudyRepository extends JpaRepository<AdviserStudy,Integer> {
}
