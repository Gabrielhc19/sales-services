package com.factusol.sales_service.utils;

import com.factusol.sales_service.dto.ProductoDTO;
import com.factusol.sales_service.repository.entity.ProductoEntity;

public abstract class Utils {

    public ProductoDTO mapToProductoDTO(ProductoEntity productoEntity) {
        ProductoDTO dto = new ProductoDTO();
        if (productoEntity != null) {
            // Actualizar los campos necesarios
            dto.setId(productoEntity.getId());
            dto.setNombre(productoEntity.getNombre());
            dto.setDescripcion(productoEntity.getDescripcion());
            dto.setStock(productoEntity.getStock());
        }

        return dto;
    }

    public ProductoEntity mapToProductoEntity(ProductoDTO producto) {
        ProductoEntity entity = new ProductoEntity();
        if (producto != null) {
            // Actualizar los campos necesarios
            entity.setId(null);
            entity.setNombre(producto.getNombre());
            entity.setDescripcion(producto.getDescripcion());
            entity.setStock(producto.getStock());
        }

        return entity;
    }
}
