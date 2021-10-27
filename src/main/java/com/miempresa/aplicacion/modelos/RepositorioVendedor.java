package com.miempresa.aplicacion.modelos;

import org.springframework.data.repository.CrudRepository;

public interface RepositorioVendedor extends CrudRepository<Vendedor,String> {
    Vendedor findByCodVendedor(String codVendedor);
    Iterable<Vendedor> findAllByOrderByCodVendedor();
}
