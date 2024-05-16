package com.sau.dims.repository;

import com.sau.dims.model.Adviser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdviserRepository extends JpaRepository<Adviser,Integer> {

    @Query("SELECT a FROM Adviser AS a ORDER BY a.id ASC")
    List<Adviser> findAllAscById();
}
