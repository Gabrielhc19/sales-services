package com.factusol.sales_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factusol.sales_service.dto.ProductoDTO;
import com.factusol.sales_service.service.ProductoService;
import com.factusol.sales_service.service.ValidationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/")
    public String home() {
        return "Bienvenido a la API de Productos!";
    }

    // GET para devolver un producto en formato JSON
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un producto por ID", notes = "Devuelve un producto por su ID")
    public ProductoDTO obtenerProductoById(
            @ApiParam(value = "ID del producto que se compra", required = true, example = "1") @PathVariable Long id) {
        return productoService.obtenerProductoById(id);
    }

    // POST para insertar un producto
    @PostMapping("/add")
    @ApiOperation(value = "Insertar un nuevo producto", notes = "Crea un nuevo producto en el sistema")
    public ProductoDTO insertarProducto(
            @ApiParam(value = "json de entrada", required = true) @RequestBody ProductoDTO nuevoProducto) {
        validationService.validarProducto(nuevoProducto); // Validación

        return productoService.insertarProducto(nuevoProducto);
    }

    // PUT para actualizar un producto existente
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualizar un producto existente, en caso de compra se llamara esto para actualizar el stock", notes = "Actualiza la información de un producto por su ID")
    public ProductoDTO actualizarProducto(
            @ApiParam(value = "ID del producto a actualizar", required = true, example = "1") @PathVariable Long id,
            @ApiParam(value = "json de entrada", required = true) @RequestBody ProductoDTO productoActualizado) {
        // por si acaso no se setea la id
        productoActualizado.setId(id);

        validationService.validarProducto(productoActualizado); // Validación

        return productoService.actualizarProducto(productoActualizado);
    }

    // PUT para actualizar un producto existente
    @PutMapping("/compra/{id}")
    @ApiOperation(value = "Actualizar un producto existente, en caso de compra se llamara esto para actualizar el stock", notes = "Actualiza la información de un producto por su ID")
    public ProductoDTO compraProducto(
            @ApiParam(value = "ID del producto que se compra", required = true, example = "1") @PathVariable Long id,
            @ApiParam(value = "cantidad de productos que se compran", required = true, example = "1") @RequestParam("cantidad") Integer cantidadCompra) {
        validationService.validarCompra(id, cantidadCompra); // Validación

        return productoService.compraProducto(id, cantidadCompra);
    }

    @ApiOperation(value = "Eliminar un producto por ID", notes = "Elimina un producto del sistema por su ID")
    @DeleteMapping("/{id}")
    public void deleteById(
            @ApiParam(value = "ID del producto a eliminar", required = true, example = "1") @PathVariable Long id) {
        validationService.validarExisteProducto(id);
        productoService.deleteById(id);
    }

    // GET para obtener todos los productos
    @GetMapping("/all")
    @ApiOperation(value = "Obtener todos los productos", notes = "Devuelve una lista de todos los productos en el sistema")
    public List<ProductoDTO> obtenerTodos() {
        return productoService.obtenerTodos();
    }
}
