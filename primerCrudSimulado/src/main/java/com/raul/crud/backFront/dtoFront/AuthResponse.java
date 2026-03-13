package com.raul.crud.backFront.dtoFront;

import lombok.Data;

@Data
public class AuthResponse {  // se crea este  paquete y clase para  generar  el  objeto  del login  con mail, password y  token
    private boolean success;
    private String message;
    private String token; // opcional, solo en login

    // Constructores
    public AuthResponse() {}

    public AuthResponse(boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }
 /*   Tipado fuerte: ya no trabajas con Map<String,Object> sino con un objeto claro (AuthResponse).

    Legibilidad: el código es más fácil de entender y mantener.

            Extensible: si mañana agregas más campos (ej. role, expiresAt), solo los añades al DTO.

            👉 Con esto tu front recibe un objeto AuthResponse directamente del microservicio, y puede mostrar mensajes o guardar el token en sesión.
*/
}
