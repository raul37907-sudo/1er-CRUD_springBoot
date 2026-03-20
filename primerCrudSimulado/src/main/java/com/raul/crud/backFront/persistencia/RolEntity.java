package com.raul.crud.backFront.persistencia;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;

    private String rol;
    @Override
    public String toString() {
        return "RolEntity{" +
                "idRol=" + id_rol +
                ", rol='" + rol + '\'' +
                '}';
    }
}
