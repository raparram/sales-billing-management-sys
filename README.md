# Sistema de Gestión de facturación y ventas

## Contexto y Tecnologías
Este software fue desarrollado con los conocimientos adquiridos en el programa Misión TIC 2021, ciclo 3. El punto de partida es la arquitectura de tres capas, se usó PostgreSQL para la base de datos, en el backend se utilizó JAVA con Spring Boot implementando dependencias atreves de Maven de herramientas como Hibernate y Thymeleaf, y el frontend fue hecho con jQuery, Bootstrap y HTML. En el futuro se incorporaran tecnologías como Vue y Angular.

## Funcionalidades
El sistema implementa un CRUD para gestionar facturas, productos y vendedores, generando registros en el tiempo de las ventas. Los ids de las facuras y vendedores son autogenerados por la base de datos, en la cual asigna a las facturas el prefijo FAC y a los vendedores VEND. En proceso se encuentran las siguientes funcionalidades:
- [ ] Validación de seguridad para ingresar a editar
- [ ] Validación de datos desde el backend
- [ ] Generación de facturas en pdf
- [ ] Lector de código de barras por cámara web
- [ ] Generación de informes de ventas en Excel
- [ ] Agregar directorio de clientes
- [ ] Agregar categorias a los productos
- [ ] Agregar manejo de Stock
- [ ] Agregar fotos de los productos
- [ ] Generación de inventario   

## Demo    
Para el despliegue en Heroku se le dio el contexto de Catálogo de Elementos Electrónicos, disponible en [Metatech](https://metatech2.herokuapp.com/), al ser un servicio gratuito se demora unos 20 segundos en cargar la página.

## Al clonar
Este proyecto se desarrolló en Apache NetBeans 12.5, una versión anterior puede servir desde que permita el uso de Maven y de Spring Boot, para la base de datos se utilizó PostgreSQL 13. Para ejecutar de manera exitosa un clon de este repositorio se recomienda usar las herramientas mencionadas, al utilizar Maven todas las dependencias serán instaladas al darle Build al proyecto, debe garantizar que el Build sea compilado con JDK 1.8. El archivo que crea la base de datos es [db_sys.sql](https://github.com/raparram/sales-billing-management-sys/blob/main/db_sys.sql).    
    
Después de terminar el proceso de Build pude ejecutar el archivo [AplicacionApplication.java](https://github.com/raparram/sales-billing-management-sys/blob/main/src/main/java/com/miempresa/aplicacion/AplicacionApplication.java), y es posible ingresar al aplicativo en http://localhost:8081/, si lo desea puede editarse el puerto y las credenciales de la base de datos en el archivo [application.yml](https://github.com/raparram/sales-billing-management-sys/blob/main/src/main/resources/application.yml).

## Vistas previas
Vista http://localhost:8081/ 
![](https://github.com/raparram/sales-billing-management-sys/blob/main/previews/previoBienvenidos.JPG)

Vista http://localhost:8081/vendedores
![](https://github.com/raparram/sales-billing-management-sys/blob/main/previews/previoVendedores.JPG)

## Citas
- Para mas información sobre queries con JPA y Spring Boot: [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface)
- Para mas información sobre Thymeleaf: https://www.thymeleaf.org/

### Notas
- Actualmente el sistema no gestiona eliminación de registros que atenten contra la integridad referencial de la base de datos.
