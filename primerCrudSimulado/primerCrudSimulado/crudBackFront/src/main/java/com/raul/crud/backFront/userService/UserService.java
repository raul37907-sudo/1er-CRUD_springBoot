package com.raul.crud.backFront.userService;

import com.raul.crud.backFront.persistencia.UserEntity;
import com.raul.crud.backFront.userDto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Crear nuevo usuario
    boolean newUser(UserDto newUser);

    // Modificar usuario
    public boolean modificarUsuario(Long id, UserDto usuario);

    // Eliminar usuario por mail
    boolean deleteUserByMail(String mail); // 👈 nombre consistente
    // Buscar usuario por ID
    Optional<UserDto> findById(Long id);

    // Listar todos los usuarios
    List<UserDto> listarAll();


}
