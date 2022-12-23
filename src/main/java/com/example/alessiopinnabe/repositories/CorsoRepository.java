package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.CorsoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<CorsoEntity, Integer> {

    @Query(value = "select * from corso where enable = 1", nativeQuery = true)
    List<CorsoEntity> getAllEnabled ();

    @Modifying
    @Query("UPDATE corso c SET c.enable = :state WHERE c.id = :id")
    int changeStatus(@Param("state") int state, @Param("id") Integer id);
}