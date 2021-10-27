package com.miempresa.aplicacion.modelos;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_vendedores")
public class Vendedor {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_vendedor")
    private String codVendedor;
    @Getter
    @Setter
    @Column(name = "nombre_vendedor")
    private String nombreVendedor;
    @Getter
    @Setter
    @Column(name = "f_nacimiento_vendedor")
    private Date fechaNacimientoVendedor;
    @Getter
    @Setter
    @Column(name = "telefono_vendedor")
    private String telefonoVendedor;
    @Getter
    @Setter
    @Column(name = "email_vendedor")
    private String emailVendedor;
    @Getter
    @Setter
    @Column(name = "direccion_vendedor")
    private String direccionVendedor;
}
