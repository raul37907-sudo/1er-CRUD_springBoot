package com.raul.primerCrudSimulado.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    UserEntity findByMail(String mail);
    void deleteByMail(String  mail);
}


/*
interface  qu e implementa  directa  de userEntity  metodos listos ,  save ,  findAll,  deleteById
consultas derivadas findByCorrei,  deleteMail,  se generan de form a automatica

 */