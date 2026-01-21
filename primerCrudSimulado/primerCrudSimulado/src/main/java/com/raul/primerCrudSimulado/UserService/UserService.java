package com.raul.primerCrudSimulado.UserService;

import com.raul.primerCrudSimulado.UserDto.UserDto;
import com.raul.primerCrudSimulado.persistencia.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean listarAll(UserDto listarAll);

    boolean deleteUser (String mail);
     boolean newUser (UserDto newUser);
     boolean modificaUser (UserDto modificaUser);

    Optional<?> findById (Long id);// busqueda  de usuario por id

    List<UserEntity> listarAll();  // uso  de  findAll () ya  implementado  dentro  de  JpaRepository
}

// matiene los  contratos  claros entre capas