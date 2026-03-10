package com.raul.crud.backFront.controller;

import com.raul.crud.backFront.persistencia.UserEntity;
import com.raul.crud.backFront.userDto.UserDto;
import com.raul.crud.backFront.userService.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    // 👉 Crear nuevo usuario
    @PostMapping("/guardar")
    public ResponseEntity<Boolean> guardarUsuario(@RequestBody UserDto usuario) {

        if (usuario.getMail()==null || usuario.getMail().isEmpty()){
            return ResponseEntity.notFound().build();
        }

        boolean creado = userService.newUser(usuario);
        return ResponseEntity.ok(creado);
    }

    // 👉 Actualizar usuario existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<Boolean> actualizarUsuario(@PathVariable Long id, @RequestBody UserDto usuario) {
        boolean actualizado = userService.modificarUsuario(id, usuario);
        if (!actualizado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(true);
    }


    // 👉 Eliminar usuario
    @DeleteMapping("/mail/{mail}")
    public ResponseEntity<Boolean> eliminarUsuarioPorMail(@PathVariable String mail) {
        boolean eliminado = userService.deleteUserByMail(mail);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(true);
    }
}





