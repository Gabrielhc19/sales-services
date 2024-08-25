package com.factusol.sales_service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    @ApiModelProperty(notes = "ID único del producto, se debe rellenar siempre que se pueda", example = "1", required = true)
    private Long id;

    @ApiModelProperty(notes = "Nombre del producto", required = true)
    private String nombre;

    @ApiModelProperty(notes = "Descripción del producto")
    private String descripcion;

    @ApiModelProperty(notes = "Cantidad disponible en stock", example = "100", required = true)
    private Integer stock;
}
