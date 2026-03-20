package com.raul.crud.backFront.controller;

import com.raul.crud.backFront.userDto.UserDto;
import com.raul.crud.backFront.userService.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    private final UserService userService;

    public UsuarioRestController(UserService userService) {
            this.userService = userService;
    }

    // 👉 Listar todos los usuarios
    @GetMapping("/listar")
    public List<UserDto> obtenerUsuarios() {
        return userService.listarAll();
    }

    // 👉 Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> obtenerUsuario(@PathVariable Long id) {

        Optional<UserDto> usuarioOpt = userService.findById(id);
        return usuarioOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 👉 Crear nuevo usuario para la tabla
 @PostMapping("/guardar")
    public ResponseEntity<Boolean> guardarUsuario(@Valid @RequestBody UserDto usuario) {  // con  el  @Valid valida las anotaciones de validacion  que  se  encuentran  en  el  UserDto
        System.out.println("+++++controlador +++++ llega  directo  del controlador que  usa  el  front" +  usuario);

        if (usuario.getMail()==null || usuario.getMail().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        boolean creado = userService.newUser(usuario);
        return ResponseEntity.ok(creado);
    }

    // 👉 Actualizar usuario existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<Boolean> actualizarUsuario(@PathVariable Long id, @RequestBody UserDto usuario) {
        System.out.println("+++controlador llega  directo al  controlador que  usa  el  front para  editar " +  usuario);
        boolean actualizado = userService.modificarUsuario(id, usuario);
        if (!actualizado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(true);

    }


    // 👉 Eliminar usuario
    @DeleteMapping("/mail/{mail}")
    public ResponseEntity<Map<String,Object>> eliminarUsuarioPorMail(@PathVariable String mail) {
        System.out.println("DELETE recibido para mail (raw): " + mail);
        boolean eliminado = userService.deleteUserByMail(mail);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("success", false, "message", "Usuario no encontrado"));
        }
        return ResponseEntity.ok(Map.of("success", true, "message", "Usuario eliminado"));
    }

}





