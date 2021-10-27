package com.miempresa.aplicacion.modelos;

import org.springframework.data.repository.CrudRepository;

public interface RepositorioFactura extends CrudRepository<Factura,String> {
   //Iterable<Factura> findByNumeroFactura(String numeroFactura);
    Factura findByNumeroFactura(String numeroFactura);
    boolean existsFacturaByNumeroFactura(String numeroFactura);
    Iterable<Factura> findAllByOrderByNumeroFactura();
}
