package com.raul.crud.backFront.userService;

import com.raul.crud.backFront.persistencia.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
    // Puedes agregar consultas derivadas si lo necesitas

    Optional<RolEntity> findByRol(String  rol); // busca por  nombre  del rol
  // RolEntity  findByIdRol(Long id); // si quieres buscar por el id del rol.


}
