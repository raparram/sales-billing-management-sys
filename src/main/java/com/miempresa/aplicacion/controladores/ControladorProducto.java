package com.miempresa.aplicacion.controladores;

import com.miempresa.aplicacion.modelos.Producto;
import com.miempresa.aplicacion.modelos.RepositorioProducto;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ControladorProducto {
    
    private final RepositorioProducto repositorioProducto;
    
    @GetMapping("/productos") //path del controlador
    public String getTodosLosProductos(Model model){
        Iterable<Producto> productos = repositorioProducto.findAllByOrderByCodProducto();
        model.addAttribute("productos",productos);
        return "vistaProducto";
    }    
    
    @GetMapping("/productos/{codigoProducto}") //path del controlador
    public String getProductoById(@PathVariable String codigoProducto, Model model){
        List<String> listaProducto = new ArrayList<>();
        listaProducto.add(codigoProducto);
        Iterable<Producto> productos = repositorioProducto.findAllById(listaProducto);
        model.addAttribute("productos",productos);
        return "vistaProducto";
    }
 
    @GetMapping("/crear/producto") //path del controlador
    public String crearProducto(Model model){
        model.addAttribute("producto",new Producto());
        return "vistaCrearProducto";
    }  
    
    @GetMapping("/actualizar/producto/{codigoProducto}")
    public String actualizarProducto(@PathVariable String codigoProducto, Model model){
        Producto productoActualizar = repositorioProducto.findByCodProducto(codigoProducto);
        model.addAttribute("producto",productoActualizar); 
        return "vistaActualizarProducto";
    } 
    
    @PostMapping("/guardar/producto")
    public RedirectView guardarProducto(@ModelAttribute Producto producto){
       Producto productoGuardado = repositorioProducto.save(producto);
       if (productoGuardado == null){
           return new RedirectView("/crear/producto/",true);
       }
       return new RedirectView("/productos/"+productoGuardado.getCodProducto(),true);
    } 
    
    @GetMapping("/eliminar/producto/{codigoProducto}")
    public String eliminarProducto(@PathVariable String codigoProducto) {
        repositorioProducto.deleteById(codigoProducto);
        return "redirect:/productos";    
    }
    
}
