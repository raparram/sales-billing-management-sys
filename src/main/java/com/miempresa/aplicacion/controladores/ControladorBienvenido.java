package com.miempresa.aplicacion.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBienvenido {
    
    @GetMapping("/") //path del controlador
    public String bienvenido(){
        return "vistaBienvenido";
    }
}
