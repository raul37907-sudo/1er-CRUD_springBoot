package com.raul.crud.backFront.controller;

import com.raul.crud.backFront.dtoFront.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8082")
@Controller
 @RequestMapping("/usuarios")  //  sobre el  8082
public class ClienteLoginController {



        // 👉 Método auxiliar para validar token
        private boolean validarToken(HttpServletRequest request) {
            String authHeader = request.getHeader("Authorization");
            return (authHeader != null && authHeader.startsWith("Bearer "));
        }

        // 👉 Mostrar formulario de login (esta sí debe ser pública)
        @GetMapping("/login")
        public String mostrarLogin() {
            return "login"; // JSP en /WEB-INF/jsp/login.jsp
        }

        // 👉 Logout (requiere token para poder cerrar sesión)
        @GetMapping("/logout")
        public String cerrarSesion(HttpSession session, HttpServletRequest request) {
            if (!validarToken(request)) {
                return "redirect:/usuarios/login"; // si no hay token, redirige al login
            }
            session.invalidate(); // invalida toda la sesión
            return "redirect:/usuarios/login";
        }
    }


