DROP TABLE  IF EXISTS t_productos;

CREATE TABLE t_productos (
	cod_producto VARCHAR(8) PRIMARY KEY,
	nombre_producto VARCHAR(30) NOT NULL,
	descripcion_producto VARCHAR(60),
	precio_producto FLOAT
);


DROP TABLE  IF EXISTS t_vendedores;
DROP SEQUENCE  IF EXISTS sequence_for_vendedores;

CREATE SEQUENCE sequence_for_vendedores
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99
  START 1
  CACHE 1;

CREATE TABLE t_vendedores (
	cod_vendedor character varying NOT NULL,
	nombre_vendedor VARCHAR(50) NOT NULL,
    f_nacimiento_vendedor DATE NOT NULL,
    telefono_vendedor VARCHAR(32),
    email_vendedor TEXT NOT NULL UNIQUE,
	direccion_vendedor CHAR(64),
	CONSTRAINT t_vendedores_pkey PRIMARY KEY (cod_vendedor)
);

ALTER TABLE t_vendedores ALTER COLUMN cod_vendedor SET DEFAULT TO_CHAR(nextval('sequence_for_vendedores'::regclass),'"VEND"fm00');


DROP TABLE  IF EXISTS t_ventas;
DROP SEQUENCE  IF EXISTS sequence_for_ventas;

CREATE SEQUENCE sequence_for_ventas
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999
  START 1
  CACHE 1;

CREATE TABLE t_ventas (
	numero_factura character varying NOT NULL,
	cod_producto VARCHAR(8) NOT NULL,
	fecha_venta DATE,
	cod_vendedor VARCHAR(8),
	valor_factura Float,
	FOREIGN KEY (cod_producto) REFERENCES t_productos (cod_producto),
	FOREIGN KEY (cod_vendedor) REFERENCES t_vendedores (cod_vendedor),
    CONSTRAINT t_ventas_pkey PRIMARY KEY (numero_factura)
);

ALTER TABLE t_ventas ALTER COLUMN numero_factura SET DEFAULT TO_CHAR(nextval('sequence_for_ventas'::regclass),'"FAC"fm000');