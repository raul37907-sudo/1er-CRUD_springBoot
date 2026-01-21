package com.raul.primerCrudSimulado.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data  // de  esta manera ya  no  es neceario ingresar  o  escribir textualmente  el  getter y  el  setter
public class UserDto {
    /*
        @notblanck , @min, @email vienen  de la  libreria jakarta y  se debe de cxolocar  en  el  pom
         */
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

}
