package com.raul.crud.backFront.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/* Cómo funciona
La anotación @Component hace que Spring Boot registre automáticamente el filtro.

Cada vez que se procesa una petición, se añaden las cabeceras anti‑cache.

Esto aplica a todas las páginas (incluyendo login.jsp), evitando que el navegador guarde credenciales o formularios en caché.

✅ Con este filtro, al regresar al login los campos estarán vacíos y el navegador no mostrará datos anteriores. */

@Component
public class NoCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Cabeceras anti-cache
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
        httpResponse.setDateHeader("Expires", 0); // Proxies

        // Continuar con la cadena de filtros
        chain.doFilter(request, response);
    }
}
