package com.sau.dims.repository;

import com.sau.dims.model.Adviser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AdviserRepository extends JpaRepository<Adviser,Integer> {

    @Query("SELECT a FROM Adviser AS a ORDER BY a.id ASC")
    List<Adviser> findAllAscById();

    boolean existsByUsername(String username);

    Optional<Adviser> findByUsername(String username);
}
