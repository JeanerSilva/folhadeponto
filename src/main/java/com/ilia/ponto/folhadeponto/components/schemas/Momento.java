package com.ilia.ponto.folhadeponto.components.schemas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.ilia.ponto.folhadeponto.validation.DataFormat;
import com.ilia.ponto.folhadeponto.validation.NotEmptyField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Momento {   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; 

    
   @NotEmptyField
   @DataFormat
   @Column(name = "datahora", length = 200)    
    private String dataHora;
}
