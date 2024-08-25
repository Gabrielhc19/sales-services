package com.factusol.sales_service.service;

import java.util.List;
import com.factusol.sales_service.dto.ProductoDTO;

public interface ProductoService {

    ProductoDTO obtenerProductoById(Long id);

    ProductoDTO insertarProducto(ProductoDTO producto);

    ProductoDTO actualizarProducto(ProductoDTO producto);

    List<ProductoDTO> obtenerTodos();

    void deleteById(Long id);

    ProductoDTO compraProducto(Long id, Integer cantidadCompra);
}
