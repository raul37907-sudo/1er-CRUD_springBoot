package com.raul.crud.backFront.persistencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
@
 ESTA  SERA MI ENTIDAD (USERENTITY)
 la cual  representara l a tabla en la BD
 */




@Entity
@Table(name = "usuarios")

@Data
public class  UserEntity { // es la persistencia

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("unused")
	private String nombre;
    @SuppressWarnings("unused")
	private String paterno;
    @SuppressWarnings("unused")
	private String materno ;
    @SuppressWarnings("unused")
	private int edad;

	private String Rol;

    @Column(unique = true)
    private String mail;




}
