package com.factusol.sales_service.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.factusol.sales_service.dto.ProductoDTO;
import com.factusol.sales_service.repository.dao.ProductoDao;
import com.factusol.sales_service.repository.entity.ProductoEntity;
import com.factusol.sales_service.utils.Utils;

@Service
public class ProductoServiceImpl extends Utils implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO obtenerProductoById(Long id) {
        try {
            // Lógica para obtener un producto de la base de datos usando el DAO
            Optional<ProductoEntity> productoOpt = productoDao.findById(id);
            if (productoOpt.isPresent()) {
                return mapToProductoDTO(productoOpt.get());
            } else {
                throw new RuntimeException("Producto con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto con ID " + id, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductoDTO insertarProducto(ProductoDTO producto) {
        try {
            // Lógica para insertar un nuevo producto en la base de datos
            ProductoEntity entity = mapToProductoEntity(producto);
            entity = productoDao.save(entity);

            // mapeo la id que se ha generado
            producto.setId(entity.getId());

            return producto;
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar el producto", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductoDTO actualizarProducto(ProductoDTO productoActualizado) {
        try {
            // Obtener el producto existente
            ProductoEntity entity = mapToProductoEntity(productoActualizado);

            productoDao.save(entity);

            // Guardar los cambios en la base de datos
            return mapToProductoDTO(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto con ID " + productoActualizado.getId(), e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductoDTO compraProducto(Long id, Integer cantidadCompra) {
        try {
            // Obtener el producto existente
            ProductoEntity entity = productoDao.getReferenceById(id);
            Integer stockAntiguo = entity.getStock();

            // en caso de ser una compra se quitara la cantidad del stock
            if (cantidadCompra != null) {
                entity.setStock(stockAntiguo - cantidadCompra);
            }

            productoDao.save(entity);

            // Guardar los cambios en la base de datos
            return mapToProductoDTO(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto con ID " + id, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        try {
            productoDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto con ID " + id, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTodos() {
        try {
            // Lógica para obtener todos los productos
            List<ProductoEntity> lstEntity = productoDao.findAll();
            List<ProductoDTO> lstProducto = new ArrayList<>();

            for (ProductoEntity productoEntity : lstEntity) {
                lstProducto.add(mapToProductoDTO(productoEntity));
            }
            return lstProducto;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los productos", e);
        }
    }
}