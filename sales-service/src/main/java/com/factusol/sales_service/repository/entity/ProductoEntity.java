package com.factusol.sales_service.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {

    @Id
    Long id;

    String nombre;
    
    String descripcion;

    Integer stock;
}
