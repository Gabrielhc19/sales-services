package com.factusol.sales_service.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.factusol.sales_service.repository.entity.ProductoEntity;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ProductoDao extends JpaRepository<ProductoEntity, Long> {
}
