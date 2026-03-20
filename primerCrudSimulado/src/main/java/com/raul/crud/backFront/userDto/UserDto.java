package com.raul.crud.backFront.userDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.raul.crud.backFront.persistencia.RolEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data   
public class UserDto {
    /*
        @notblanck , @min, @email vienen  de la  libreria jakarta y  se debe de cxolocar  en  el  pom
         */

	private Long id;

    @NotBlank(message = "el nombre no  puede estas vacio")
    private String nombre;
	@NotBlank(message = "el apellido paterno  no  puede estas vacio")
    private String paterno;
    @NotBlank(message = "el apellido materno  no  puede estas vacio")
    private String materno;
    @Min(value = 18,  message = "Debes de ser mayor de edad ")
    private int edad;

    @Email(message = "el e-mail  debe  de tener  un formato  valido")
    @NotBlank(message = "el correo  no puede estas en  blanco")
    private String mail;


	@NotNull
	//@JsonProperty("id_rol")   // opcional: mantiene el nombre id_rol que llega  en  le   JSON
    // se utiliza en  caso  de qu e la  variable de esta clase tenga otro  nombre y
	private Long idRolDto;
	private  String nomRol;









	}



