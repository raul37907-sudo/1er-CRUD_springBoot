package com.raul.primerCrudSimulado.UserServiceImp;

import com.raul.primerCrudSimulado.UserDto.UserDto;
import com.raul.primerCrudSimulado.UserService.UserService;

import com.raul.primerCrudSimulado.persistencia.UserEntity;
import com.raul.primerCrudSimulado.persistencia.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public boolean newUser(UserDto newUser) {
        if (userRepository.findByMail(newUser.getMail()) != null)  return  false ; // ya existe el  correo
        UserEntity entity = mapToEntity (newUser);
        userRepository.save(entity);
        return true;  //si se  guardo el  nuevo  usuario
    }

    @Override
    public boolean modificaUser(UserDto modificationUser) {
        UserEntity userExiste = userRepository.findByMail(modificationUser.getMail());
        if (userExiste == null) return false ;
        userExiste.setNombre(modificationUser.getNombre());
        userExiste.setMaterno(modificationUser.getMaterno());
        userExiste.setPaterno(modificationUser.getPaterno());
        userExiste.setEdad(modificationUser.getEdad());
        userRepository.save(userExiste);
        return true;
    }

    @Override
    public boolean listarAll(UserDto listarAll) {
        return true;
    }


    @Override
    public boolean deleteUser(String mail) {
        UserEntity existUser = userRepository.findByMail(mail);
        if (existUser == null) return false ;
        userRepository.delete(existUser);
        return true; //
    }

    public Optional<UserEntity> findById (Long id){
        return userRepository.findById(id); // JpaRepository ya lo implementa
    }

    @Override
    public List<UserEntity> listarAll() {
       return userRepository.findAll() ;
    }

    private UserEntity mapToEntity (UserDto dto){
        UserEntity e = new UserEntity();
        e.setNombre(dto.getNombre());
        e.setPaterno(dto.getPaterno());
        e.setMaterno(dto.getMaterno());
        e.setEdad(dto.getEdad());
        e.setMail(dto.getMail());
        return  e;
    }


   

    /*
    se mapea el  dto -> entidad  metodo  mapToEntity

    reglas simples no  crear  si  el  correo  ya esxiste ; actaulizar  poir  correo, buscar  uno por uno  por medi o del  id

     */



}
