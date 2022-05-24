package com.ilia.ponto.folhadeponto.repository;

import java.util.List;
import com.ilia.ponto.folhadeponto.components.schemas.Momento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface Momentos extends JpaRepository<Momento, Integer > {

    @Query(value = " select * from momento c where c.datahora like %:d% ", nativeQuery = true)
    List<Momento> findByDataHora(String d);
    
    
}

