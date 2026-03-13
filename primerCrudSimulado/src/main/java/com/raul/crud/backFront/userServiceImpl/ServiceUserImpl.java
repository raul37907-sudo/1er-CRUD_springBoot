package com.raul.crud.backFront.userServiceImpl;

import com.raul.crud.backFront.userDto.UserDto;
import com.raul.crud.backFront.persistencia.UserEntity;
import com.raul.crud.backFront.persistencia.UserRepository;
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

    public ServiceUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   

    @Override
    public boolean newUser(@Valid UserDto newUser) {
        if (userRepository.findByMail(newUser.getMail()) != null) return false;
        UserEntity entity = mapToEntity(newUser);
        userRepository.save(entity);
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
        existente.setRol(usuario.getRol());

        userRepository.save(existente); // ✅ ahora sí guardas la entidad
        return true;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(entity -> new UserDto(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getPaterno(),
                        entity.getMaterno(),
                        entity.getEdad(),
                        entity.getMail(),
                        entity.getRol()
                ));
    }//



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
                .map(entity -> new UserDto(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getPaterno(),
                        entity.getMaterno(),
                        entity.getEdad(),
                        entity.getMail(),
                        entity.getRol()
                ))
                .collect(Collectors.toList());
    }

    private UserEntity mapToEntity(UserDto dto) {
        UserEntity e = new UserEntity();
        e.setNombre(dto.getNombre());
        e.setPaterno(dto.getPaterno());
        e.setMaterno(dto.getMaterno());
        e.setEdad(dto.getEdad());
        e.setMail(dto.getMail());
        e.setRol(dto.getRol());
        return e;
    }
}




   

    /*
    se mapea el  dto -> entidad  metodo  mapToEntity

    reglas simples no  crear  si  el  correo  ya esxiste ; actaulizar  poir  correo, buscar  uno por uno  por medi o del  id

     */




