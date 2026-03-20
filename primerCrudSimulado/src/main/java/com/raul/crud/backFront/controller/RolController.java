package com.raul.crud.backFront.controller;

import com.raul.crud.backFront.persistencia.RolEntity;
import com.raul.crud.backFront.userDto.RolDto;
import com.raul.crud.backFront.userService.RolRepository;
import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @GetMapping("/listar")
    // esto  te  devuelve  la  entidad tal  cual , por integridad total  mejo r mandamos  un dto    public List<RolEntity> getListRoles(){
    public List<RolDto> getListRoles() {

        return  rolRepository.findAll()
                .stream()
                .map(r -> {  //.map(...) transforma cada RolEntity en un RolDto.
                    RolDto rolDto = new RolDto();
                    rolDto.setIdRolDto(r.getId_rol());
                    rolDto.setRolNomDto(r.getRol());
                System.out.println(" DTO creado :" + rolDto) ;
                    return rolDto;
                })
                .collect(Collectors.toList()); // <-- aquí cierras el stream  .collect(Collectors.toList()) convierte el stream en una lista de DTOs.
    }

}