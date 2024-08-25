package com.factusol.sales_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

import com.factusol.sales_service.dto.ProductoDTO;
import com.factusol.sales_service.repository.dao.ProductoDao;
import com.factusol.sales_service.repository.entity.ProductoEntity;

@Component
public class ValidationService {

    @Autowired
    private ProductoDao productoDao;

    public void validarProducto(ProductoDTO producto) {
        validarExisteProducto(producto.getId());
        
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre del producto es obligatorio");
        }
        if (producto.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }
    }

    public void validarExisteProducto(Long id) {
        if (!productoDao.existsById(id)) {
            throw new RuntimeException("Producto con ID " + id + " no encontrado.");
        }
    }

    public void validarCompra(Long id, Integer cantidadCompra) {
        Optional<ProductoEntity> productoOpt = productoDao.findById(id);
        ProductoEntity entity = productoOpt.get();
        if (!productoOpt.isPresent()) {
            throw new RuntimeException("Producto con ID " + id + " no encontrado.");
        }
        if (cantidadCompra == null || cantidadCompra <= 0) {
            throw new RuntimeException("La cantidad de productos comprados no puede ser 0 o menor que 0");
        }
        if (entity.getStock() != null && entity.getStock() < cantidadCompra) {
            throw new RuntimeException("El producto no tiene suficiente stock");
        }
    }
}
