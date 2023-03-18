package com.example.alessiopinnabe.repositories;


import com.example.alessiopinnabe.entity.Acquisto;
import com.example.alessiopinnabe.entity.ImgServizio;
import com.example.alessiopinnabe.entity.Servizio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<ImgServizio, String> {

    long deleteByServizio(Servizio servizio);
}
