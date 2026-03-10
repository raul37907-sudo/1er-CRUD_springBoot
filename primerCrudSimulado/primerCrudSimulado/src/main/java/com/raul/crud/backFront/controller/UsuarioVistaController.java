package com.raul.crud.backFront.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioVistaController {


    // Vista de lista de usuarios
    @GetMapping("/listaUsuarios")
    public String mostrarListaUsuarios() {
        return "listaUsuarios"; // JSP en /WEB-INF/jsp/listaUsuarios.jsp
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear() {
        return "nuevoUsuario"; // nombre del JSP
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "editarUsuario"; // JSP de edición
    }

    // 👉 Registro de nuevo usuario (público, SIN protección)
    @GetMapping("/crearNewUser")
    public String mostrarRegistro() {
        return "newUserSesion"; // JSP en /WEB-INF/jsp/newUsersesion.jsp }

    }
}


/* 👉 Método auxiliar para validar token
private boolean validarToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    return (authHeader != null && authHeader.startsWith("Bearer "));
}

/ 👉 Vista de lista de usuarios
@GetMapping("/listaUsuarios")
public String mostrarListaUsuarios(HttpServletRequest request) {
    if (!validarToken(request)) {
        return "redirect:/usuarios/login"; // redirige al login si no hay token
    }
    return "listaUsuarios"; // JSP en /WEB-INF/jsp/listaUsuarios.jsp
}


@GetMapping("/crear")
public String mostrarFormularioCrear(HttpServletRequest request) {
    if (!validarToken(request)) {
        return "redirect:/usuarios/login";
    }
    return "nuevoUsuario"; // JSP
}

@GetMapping("/editar/{id}")
public String editarUsuario(@PathVariable Long id, Model model, HttpServletRequest request) {
    if (!validarToken(request)) {
        return "redirect:/usuarios/login";
    }
    model.addAttribute("id", id);
    return "editarUsuario"; // JSP de edición
}
    }*/
