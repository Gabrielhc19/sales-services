// package com.factusol.sales_service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import static org.mockito.Mockito.eq;
// import static org.mockito.Mockito.anyLong;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.factusol.sales_service.dto.ProductoDTO;
// import com.factusol.sales_service.repository.dao.ProductoDao;
// import com.factusol.sales_service.repository.entity.ProductoEntity;
// import com.factusol.sales_service.service.ProductoServiceImpl;

// @SpringBootTest
// class ProductoServiceImplTest {

//     @InjectMocks
//     private ProductoServiceImpl productoService;

//     @Mock
//     private ProductoDao productoDao;

//     private ProductoDTO productoDTO;
//     private ProductoEntity productoEntity;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this); // Inicializar mocks

//         productoDTO = new ProductoDTO();
//         productoDTO.setId(1L);
//         productoDTO.setNombre("Producto de prueba");
//         productoDTO.setDescripcion("Descripción de prueba");
//         productoDTO.setStock(100);

//         productoEntity = new ProductoEntity();
//         productoEntity.setId(1L);
//         productoEntity.setNombre("Producto de prueba");
//         productoEntity.setDescripcion("Descripción de prueba");
//         productoEntity.setStock(100);
//     }

//     @Test
//     void obtenerProducto_existente() {
//         // Simular la obtención de un producto por su ID
//         when(productoDao.findById(anyLong())).thenReturn(Optional.of(productoEntity));

//         // Mockear el mapeo de ProductoEntity a ProductoDTO
//         when(productoService.mapToProductoDTO(any(ProductoEntity.class))).thenReturn(productoDTO);

//         // Ejecutar el método
//         ProductoDTO resultado = productoService.obtenerProducto(1L);

//         // Verificar que el resultado no sea null
//         assertNotNull(resultado);
//         assertEquals(1L, resultado.getId());
//     }

//     @Test
//     void obtenerProducto_noExistente() {
//         when(productoDao.findById(1L)).thenReturn(Optional.empty());

//         Exception exception = assertThrows(RuntimeException.class, () -> {
//             productoService.obtenerProducto(1L);
//         });

//         assertEquals("Producto con ID 1 no encontrado.", exception.getMessage());
//         verify(productoDao, times(1)).findById(1L);
//     }

//     @Test
//     void insertarProducto() {
//         // Mockear el mapeo de ProductoDTO a ProductoEntity
//         when(productoService.mapToProductoEntity(any(ProductoDTO.class))).thenReturn(productoEntity);

//         // Mockear la llamada a save
//         when(productoDao.save(any(ProductoEntity.class))).thenReturn(productoEntity);

//         // Ejecutar el método a testear
//         ProductoDTO resultado = productoService.insertarProducto(productoDTO);

//         // Verificar interacciones
//         verify(productoDao, times(1)).save(any(ProductoEntity.class));

//         // Verificar que el resultado es el esperado
//         assertEquals(1L, resultado.getId());
//     }

//     @Test
//     void actualizarProducto_sinCantidadCompra() {
//         // Mockear la llamada a productoDao.getReferenceById
//         when(productoDao.getReferenceById(anyLong())).thenReturn(productoEntity);

//         // Mockear el mapeo de ProductoDTO a ProductoEntity
//         when(productoService.mapToProductoEntity(any(ProductoDTO.class))).thenReturn(productoEntity);

//         // Mockear el mapeo de ProductoEntity a ProductoDTO
//         when(productoService.mapToProductoDTO(any(ProductoEntity.class))).thenReturn(productoDTO);

//         // Ejecutar el método a testear
//         ProductoDTO resultado = productoService.actualizarProducto(1L, productoDTO, null);

//         // Verificar interacciones
//         verify(productoDao, times(1)).getReferenceById(anyLong());
//         verify(productoDao, times(1)).save(any(ProductoEntity.class));

//         // Verificar que el resultado es el esperado
//         assertEquals("Producto de prueba", resultado.getNombre());
//     }

//     @Test
//     void actualizarProducto_conCantidadCompra() {
//         // Mockear la llamada a productoDao.getReferenceById
//         when(productoDao.getReferenceById(anyLong())).thenReturn(productoEntity);

//         // Mockear el mapeo de ProductoDTO a ProductoEntity
//         when(productoService.mapToProductoEntity(any(ProductoDTO.class))).thenReturn(productoEntity);

//         // Mockear el mapeo de ProductoEntity a ProductoDTO
//         when(productoService.mapToProductoDTO(any(ProductoEntity.class))).thenReturn(productoDTO);

//         // Ejecutar el método a testear
//         Integer cantidadCompra = 10;
//         ProductoDTO resultado = productoService.actualizarProducto(1L, productoDTO, cantidadCompra);

//         // Verificar interacciones
//         verify(productoDao, times(1)).getReferenceById(anyLong());
//         verify(productoDao, times(1)).save(any(ProductoEntity.class));

//         // Verificar que el stock se ha actualizado correctamente
//         assertEquals(90, productoEntity.getStock());
//     }

//     @Test
//     void deleteById() {
//         doNothing().when(productoDao).deleteById(1L);

//         productoService.deleteById(1L);

//         verify(productoDao, times(1)).deleteById(1L);
//     }

//     @Test
//     void obtenerTodos() {
//         // Simular la llamada a findAll del productoDao
//         List<ProductoEntity> listaProductos = new ArrayList<>();
//         listaProductos.add(productoEntity);

//         when(productoDao.findAll()).thenReturn(listaProductos);

//         // Mockear el mapeo de ProductoEntity a ProductoDTO
//         when(productoService.mapToProductoDTO(any(ProductoEntity.class))).thenReturn(productoDTO);

//         // Ejecutar el método a testear
//         List<ProductoDTO> resultado = productoService.obtenerTodos();

//         // Verificar que el resultado tiene el tamaño correcto
//         assertEquals(1, resultado.size());
//         verify(productoDao, times(1)).findAll();
//     }

// }
