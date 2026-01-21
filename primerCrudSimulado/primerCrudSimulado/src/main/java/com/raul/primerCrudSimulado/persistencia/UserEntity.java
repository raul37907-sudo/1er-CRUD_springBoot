package com.raul.primerCrudSimulado.persistencia;
/*
@
 ESTA  SERA MI ENTIDAD (USERENTITY)
 la cual  representara l a tabla en la BD
 */


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data  // con  esto  y a no escribes  los  getter y  setter
public class  UserEntity { // es la persistencia

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String paterno;
    private String materno ;
    private int edad;

    @Column(unique = true)
    private String mail;
    
    
}
