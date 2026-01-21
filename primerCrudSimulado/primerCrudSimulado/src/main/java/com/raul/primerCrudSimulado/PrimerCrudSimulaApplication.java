package com.raul.primerCrudSimulado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.raul.primerCrudSimulado")

public class PrimerCrudSimulaApplication {

    public static void main(String[] args) {
		SpringApplication.run(PrimerCrudSimulaApplication.class, args);
	}


	/*
	#crea la  BD primer_crud en  tu motor (Mysql,Postgres,etc)

# ddl-auto update crea/actualiza tablas segun tus entidades

#paso 1 se configura  la conexión a la  base  de datos
#paso 2 crear la entidad (UserEntity)
#paso 3 crea el  repositorio (UserRepository)
#paso 4 crea el  DTO (userDto) con  validaciones
#paso 5 crea e servicio (ServiceUserImp  es la  logica del  negocio crea ,  buscar,  actualizar ,  borrar
#paso 6  define l a interfaz del  servicio (UserService)
#paso 7 crear el  controlador (UserController)
#paso 8 probar  ya  en  posman/cUrl


el  borrado  es por  correo  http://localhost:8888/usuarios/ana@example.com

get   general  http://localhost:8888/usuarios
get  por  id http://localhost:8888/usuarios/3

post    "nombre": "Ana",
     "paterno": "López",
     "materno": "García",
      "edad": 28,
       "mail": "ana@example.com"}

       put    -->  mismo que el  post solo  que  ta  con  los datros  modificados
          "nombre": "Anaaa",
     "paterno": "Lópezzz",
     "materno": "Garcíaaa",
      "edad": 32,
       "mail": "ana@example.com"}

	 */


}
