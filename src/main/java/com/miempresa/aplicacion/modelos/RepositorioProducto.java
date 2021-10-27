package com.miempresa.aplicacion.modelos;

import org.springframework.data.repository.CrudRepository;

public interface RepositorioProducto extends CrudRepository<Producto,String> {
    Producto findByCodProducto(String codProducto);
    Iterable<Producto> findAllByOrderByCodProducto();
}
