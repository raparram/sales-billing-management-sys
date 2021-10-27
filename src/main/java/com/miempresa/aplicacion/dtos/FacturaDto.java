package com.miempresa.aplicacion.dtos;


import java.sql.Date;
import lombok.Data;


@Data
public class FacturaDto {
    private String numeroFactura;
    private String codigoProducto;
    private Date fechaVenta;
    private String codigoVendedor;
    private Float valorFactura;    
}
