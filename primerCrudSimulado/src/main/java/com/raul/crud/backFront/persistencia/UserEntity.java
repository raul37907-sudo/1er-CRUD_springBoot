package com.raul.crud.backFront.persistencia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

/*
@
 ESTA  SERA MI ENTIDAD (USERENTITY)
 la cual  representara la tabla en la BD
 */

@Entity
@Table(name = "usuarios")
@Data
public class  UserEntity { // es la persistencia

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("unused")
	private String nombre;    @SuppressWarnings("unused")
	private String paterno;    @SuppressWarnings("unused")
	private String materno ;    @SuppressWarnings("unused")
	private int edad;

    //relacion  conrol 👉 Con esto, la relación queda bidireccional:
    //Desde un Usuario puedes acceder a su Rol.
    //Desde un Rol puedes acceder a todos los Usuarios que lo tienen.
    @ManyToOne
    @JoinColumn(name = "rol", referencedColumnName = "id_rol", nullable = false)
    // name -> nombre  de la columna --  referencedColumnName columna  a la que referencía
   // @JsonBackReference
    private RolEntity  rol;  // asi  se llama la  columna de l a tabla  usuarios

    @Column(unique = true)
    private String mail;



}
