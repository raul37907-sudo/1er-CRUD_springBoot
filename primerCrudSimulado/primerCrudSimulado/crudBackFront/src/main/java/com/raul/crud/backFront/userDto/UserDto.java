package com.raul.crud.backFront.userDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
	private String rol;




		// ✅ Constructor vacío (necesario para frameworks como Jackson)
		public UserDto() {}

		// ✅ Constructor completo y  debe  de coincidir  con el ,map de la implementacion
		public UserDto(Long id, String nombre ,String paterno,String materno,int edad,String mail,String rol) {
			this.id = id;
			this.nombre = nombre;
			this.paterno = paterno;
			this.materno = materno;
			this.edad = edad;
			this.mail = mail;
			this.rol = rol;
		}

		// ✅ Constructor parcial (solo id, mail y rol)
		public UserDto(Long id, String mail, String rol) {
			this.id = id;
			this.mail = mail;
			this.rol = rol;
		}




	// Getters y setters...
	}



