package com.raul.crud.backFront.userServiceImpl;

import com.raul.crud.backFront.persistencia.RolEntity;
import com.raul.crud.backFront.userDto.UserDto;
import com.raul.crud.backFront.persistencia.UserEntity;
import com.raul.crud.backFront.userService.RolRepository;
import com.raul.crud.backFront.userService.UserRepository;
import com.raul.crud.backFront.userService.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
se genera  la onotación indoicando que sera un  servicio
 */
@Service
public class ServiceUserImpl implements UserService {
    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    public ServiceUserImpl(UserRepository userRepository, RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

   

    @Override
    public boolean newUser(@Valid UserDto newUser) {

        System.out.println("++++++++++++++++++++++++++++ ya  para  guardar  a la  base  de datos " +  newUser);


        if (userRepository.findByMail(newUser.getMail()) != null) return false;
        UserEntity entity = new UserEntity();
        entity.setMail(newUser.getMail());
        entity.setNombre(newUser.getNombre());
        entity.setPaterno(newUser.getPaterno());
        entity.setMaterno(newUser.getMaterno());
        entity.setEdad(newUser.getEdad());
        // de esta  manera  lo  guardamos  para  matenener l a relacion   de userentity  en  privat e RolEntity rol

        RolEntity rol = rolRepository.findById(newUser.getIdRolDto())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
        entity.setRol(rol);

        userRepository.save(entity); // ✅ ahora sí guardas la entidad
        return true;
    }

    @Override
    public boolean modificarUsuario(Long id, UserDto usuario) {
        Optional<UserEntity> existenteOpt = userRepository.findById(id);
        if (existenteOpt.isEmpty()) {
            return false;
        }

        UserEntity existente = existenteOpt.get();
        existente.setNombre(usuario.getNombre());
        existente.setMail(usuario.getMail());
        existente.setEdad(usuario.getEdad());
        existente.setMaterno(usuario.getMaterno());
        existente.setPaterno(usuario.getPaterno());
        // existente.setRol(usuario.getRol()); ya  biene  de una  tabl a doirecto

        // ✅ convertir el id del rol en RolEntity
        RolEntity rol = rolRepository.findById(usuario.getIdRolDto())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        existente.setRol(rol);

        //existente.setRol(String.valueOf(rol));
        //rol  es una  entidad,  casteamso para que sea string

        userRepository.save(existente); // ✅ ahora sí guardas la entidad
        return true;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    UserDto dto = new UserDto();
                    dto.setId(entity.getId());
                    dto.setNombre(entity.getNombre());
                    dto.setPaterno(entity.getPaterno());
                    dto.setMaterno(entity.getMaterno());
                    dto.setEdad(entity.getEdad());
                    dto.setMail(entity.getMail());
                    dto.setIdRolDto(entity.getRol().getId_rol());
                    dto.setNomRol(entity.getRol().getRol());
                    return dto;
                });
    }



    @Override
    public boolean deleteUserByMail(String mail) {
        UserEntity existente = userRepository.findByMail(mail);
        if (existente == null) {
            return false;
        }
        userRepository.delete(existente);
        return true;
    }





   /* @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(entity -> new UserDto(entity.getId(), entity.getMail(), entity.getRol()));
    }   */

    @Override
    public List<UserDto> listarAll() {
        return userRepository.findAll()
                .stream()
                .map(entity -> {
                    UserDto dto = new UserDto();
                    dto.setId(entity.getId());
                    dto.setNombre(entity.getNombre());
                    dto.setPaterno(entity.getPaterno());
                    dto.setMaterno(entity.getMaterno());
                    dto.setEdad(entity.getEdad());
                    dto.setMail(entity.getMail());
                    dto.setIdRolDto(entity.getRol().getId_rol());     // ✅ id del rol
                    dto.setNomRol(entity.getRol().getRol());   // ✅ nombre del rol
                    return dto;
                })
                .collect(Collectors.toList());
    }


    private UserEntity mapToEntity(UserDto dto) {
        UserEntity e = new UserEntity();
        e.setNombre(dto.getNombre());
        e.setPaterno(dto.getPaterno());
        e.setMaterno(dto.getMaterno());
        e.setEdad(dto.getEdad());
        e.setMail(dto.getMail());
        // rol  ya es entidad e.setRol(dto.getRol());
        // ✅ convertir el id del rol en RolEntity
        RolEntity rol = rolRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        e.setRol(rol);
        // rol es una entidad y  necesitamos  mandar  un string  , solo  casteamos

        return e;
    }
}




   

    /*
    se mapea el  dto -> entidad  metodo  mapToEntity

    reglas simples no  crear  si  el  correo  ya esxiste ; actaulizar  poir  correo, buscar  uno por uno  por medi o del  id

     */




