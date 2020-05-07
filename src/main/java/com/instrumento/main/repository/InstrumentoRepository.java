package com.instrumento.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instrumento.main.entities.Instrumento;



@Repository
public interface InstrumentoRepository extends  JpaRepository<Instrumento, Integer>{

}