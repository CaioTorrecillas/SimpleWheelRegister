package com.example.demo.repository;

import com.example.demo.dto.RodasDto;
import com.example.demo.model.Rodas;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RodasRepository extends JpaRepository <Rodas, Long> {

    @Query("SELECT obj FROM Rodas obj WHERE obj.marca.id = :id")
    List<Rodas> findAllByMarca(@Param("id") Long id);






}
