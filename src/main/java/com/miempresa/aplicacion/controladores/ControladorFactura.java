package com.miempresa.aplicacion.controladores;

import com.miempresa.aplicacion.dtos.FacturaDto;
import com.miempresa.aplicacion.modelos.RepositorioFactura;
import com.miempresa.aplicacion.modelos.Factura;
import com.miempresa.aplicacion.modelos.Producto;
import com.miempresa.aplicacion.modelos.RepositorioProducto;
import com.miempresa.aplicacion.modelos.RepositorioVendedor;
import com.miempresa.aplicacion.modelos.Vendedor;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
public class ControladorFactura {

    private final RepositorioFactura repositorioFactura;
    private final RepositorioProducto repositorioProducto;
    private final RepositorioVendedor repositorioVendedor;

    private FacturaDto facturaEntity2DTO(Factura factura) {
        if (factura == null) {
            return null;
        }
        Producto productoFactura = factura.getProducto();
        Vendedor vendedorFactura = factura.getVendedor();
        FacturaDto facturaDTO = new FacturaDto();
        facturaDTO.setNumeroFactura(factura.getNumeroFactura());
        facturaDTO.setCodigoProducto(productoFactura.getCodProducto());
        facturaDTO.setFechaVenta(factura.getFechaVenta());
        facturaDTO.setCodigoVendedor(vendedorFactura.getCodVendedor());
        facturaDTO.setValorFactura(factura.getValorFactura());
        return facturaDTO;
    }

    private Factura facturaDTO2Entity(FacturaDto facturaDTO, Factura facturaEntity) {
        Producto producto = repositorioProducto.findByCodProducto(facturaDTO.getCodigoProducto());
        Vendedor vendedor = repositorioVendedor.findByCodVendedor(facturaDTO.getCodigoVendedor());
        facturaEntity.setNumeroFactura(facturaDTO.getNumeroFactura());
        facturaEntity.setProducto(producto);
        facturaEntity.setVendedor(vendedor);
        facturaEntity.setFechaVenta(facturaDTO.getFechaVenta());
        facturaEntity.setValorFactura(facturaDTO.getValorFactura());
        return facturaEntity;
    }

    @GetMapping("/facturas") //path del controlador
    public String getTodasLasFacturas(Model model) {
        Iterable<Factura> facturas = repositorioFactura.findAllByOrderByNumeroFactura();
        List<FacturaDto> facturasDTOlist = new ArrayList<>();
        for (Factura factura : facturas) {
            FacturaDto facturaDTO = facturaEntity2DTO(factura);
            facturasDTOlist.add(facturaDTO);
        }
        Iterable<FacturaDto> facturasDTO = facturasDTOlist;
        model.addAttribute("facturas", facturasDTO);
        return "vistaFactura";
    }

    @GetMapping("/facturas/{numeroFactura}") //path del controlador
    public String getFacturaByNumero(@PathVariable String numeroFactura, Model model) {
        Factura factura = repositorioFactura.findByNumeroFactura(numeroFactura);
        FacturaDto facturaDTO = facturaEntity2DTO(factura);
        List<FacturaDto> facturasDTOlist = new ArrayList<>();
        if (facturaDTO != null) {
            facturasDTOlist.add(facturaDTO);
        }
        Iterable<FacturaDto> facturasDTO = facturasDTOlist;
        model.addAttribute("facturas", facturasDTO);
        return "vistaFactura";
    }

    @GetMapping("/crear/factura") //path del controlador
    public String crearFactura(Model model) {
        Iterable<Vendedor> vendedores = repositorioVendedor.findAllByOrderByCodVendedor();
        Iterable<Producto> productos = repositorioProducto.findAllByOrderByCodProducto();
        model.addAttribute("factura", new FacturaDto());
        model.addAttribute("vendedores", vendedores);
        model.addAttribute("productos", productos);
        return "vistaCrearFactura";
    }

    @GetMapping("/actualizar/factura/{numeroFactura}")
    public String actualizarFactura(@PathVariable String numeroFactura, Model model) {
        Factura facturaActualizar = repositorioFactura.findByNumeroFactura(numeroFactura);
        FacturaDto facturaDTO = facturaEntity2DTO(facturaActualizar);
        Iterable<Vendedor> vendedores = repositorioVendedor.findAllByOrderByCodVendedor();
        Iterable<Producto> productos = repositorioProducto.findAllByOrderByCodProducto();
        model.addAttribute("factura", facturaDTO);
        model.addAttribute("vendedores", vendedores);
        model.addAttribute("productos", productos);
        return "vistaActualizarFactura";
    }

    @PostMapping("/guardar/factura")
    public RedirectView guardarNuevaFactura(@ModelAttribute FacturaDto facturaDTO) {
        String numeroFactura = facturaDTO.getNumeroFactura();
        Factura factura;
        if (repositorioFactura.existsFacturaByNumeroFactura(numeroFactura) == true) {
            factura = repositorioFactura.findByNumeroFactura(numeroFactura);
        } else {
            factura = new Factura();
        }
        factura = facturaDTO2Entity(facturaDTO, factura);
        Factura facturaGuardada = repositorioFactura.save(factura);
        if (facturaGuardada == null) {
            return new RedirectView("/crear/factura/", true);
        }
        return new RedirectView("/facturas/" + facturaGuardada.getNumeroFactura(), true);
    }

    @GetMapping("/eliminar/factura/{numeroFactura}")
    public String eliminarFactura(@PathVariable String numeroFactura) {
        repositorioFactura.deleteById(numeroFactura);
        return "redirect:/facturas";
    }
}
