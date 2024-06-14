package com.example.Desafio.Pic_Pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Desafio.Pic_Pay.Entity.Transferencia;

@Repository
public interface TranferenciaRepository extends JpaRepository<Transferencia, Integer> {

}
