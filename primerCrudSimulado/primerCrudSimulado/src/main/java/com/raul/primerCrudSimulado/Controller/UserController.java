package com.raul.primerCrudSimulado.Controller;

import com.raul.primerCrudSimulado.UserDto.UserDto;
import com.raul.primerCrudSimulado.UserServiceImp.ServiceUserImpl;
import com.raul.primerCrudSimulado.persistencia.UserEntity;
import com.raul.primerCrudSimulado.persistencia.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final ServiceUserImpl service;

    public UserController(ServiceUserImpl service, UserRepository userRepository) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity <String> crear (@Valid @RequestBody UserDto dto){
        boolean ok = service.newUser(dto);
        return  ok ? ResponseEntity.ok(" Usuario  creado")
                    : ResponseEntity.badRequest().body(" El  correo  ya existe");
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@Valid @RequestBody UserDto dto){
        boolean ok = service.modificaUser(dto);
        return ok ? ResponseEntity.ok(" Usuario  actualizado")
                  : ResponseEntity.badRequest().body(" el correo  no  existe");
    }

    @DeleteMapping ("/{correo}")
    public ResponseEntity<String> borrar (@PathVariable String correo){
        boolean ok = service.deleteUser(correo);
        return ok ? ResponseEntity.ok(" Usuario  borrado")
                  : ResponseEntity.badRequest().body(" Usuario no  encontrado ");
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> obtenerUnoAUno(@PathVariable Long id) {
        return service.findById(id) // devuelve Optional<UserEntity>
                .map(ResponseEntity::ok) // si existe, 200 OK con el usuario
                .orElseGet(() -> ResponseEntity.notFound().build()); // si no existe, 404 Not Found
    }



    @GetMapping  //  par a la lista en  generarl  no  se generarn  los  parentesis solo  ek   @getmapping
    public ResponseEntity<List<UserEntity>> listarTodos() {
        List<UserEntity> usuarios = service.listarAll();
        return ResponseEntity.ok(usuarios); }

}
