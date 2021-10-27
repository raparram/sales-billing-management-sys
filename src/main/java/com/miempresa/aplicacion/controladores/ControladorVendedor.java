package com.miempresa.aplicacion.controladores;

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
public class ControladorVendedor {

    private final RepositorioVendedor repositorioVendedor;

    @GetMapping("/vendedores") //path del controlador
    public String getTodosLosVendedores(Model model) {
        Iterable<Vendedor> vendedores = repositorioVendedor.findAllByOrderByCodVendedor();
        model.addAttribute("vendedores", vendedores);
        return "vistaVendedor";
    }

    @GetMapping("/vendedores/{codigoVendedor}") //path del controlador
    public String getVendedorById(@PathVariable String codigoVendedor, Model model) {
        List<String> listaVendedor= new ArrayList<>();
        listaVendedor.add(codigoVendedor);
        Iterable<Vendedor> vendedores = repositorioVendedor.findAllById(listaVendedor);
        model.addAttribute("vendedores", vendedores);
        return "vistaVendedor";
    }

    @GetMapping("/crear/vendedor") //path del controlador
    public String crearVendedor(Model model) {
        model.addAttribute("vendedor", new Vendedor());
        return "vistaCrearVendedor";
    }

    @GetMapping("/actualizar/vendedor/{codigoVendedor}")
    public String actualizarVendedor(@PathVariable String codigoVendedor, Model model) {
        Vendedor vendedorActualizar = repositorioVendedor.findByCodVendedor(codigoVendedor);
        model.addAttribute("vendedor", vendedorActualizar);
        return "vistaActualizarVendedor";
    }

    @PostMapping("/guardar/vendedor")
    public RedirectView guardarVendedor(@ModelAttribute Vendedor vendedor) {
        Vendedor vendedorGuardado = repositorioVendedor.save(vendedor);
        if (vendedorGuardado == null) {
            return new RedirectView("/crear/vendedor/", true);
        }
        return new RedirectView("/vendedores/" + vendedorGuardado.getCodVendedor(), true);
    }

    @GetMapping("/eliminar/vendedor/{codigoVendedor}")
    public String eliminarVendedor(@PathVariable String codigoVendedor) {
        repositorioVendedor.deleteById(codigoVendedor);
        return "redirect:/vendedores";
    }
}
