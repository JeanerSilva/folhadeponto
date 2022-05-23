package com.ilia.ponto.folhadeponto.repository;

import com.ilia.ponto.folhadeponto.components.schemas.Momento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Momentos extends JpaRepository<Momento, Integer > {
    
}
