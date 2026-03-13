package com.raul.crud.backFront.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    UserEntity findByMail(String mail);
   // void deleteByMail(String  mail);
}


/*
interface  que implementa  directa  de userEntity  metodos listos ,  save ,  findAll,  deleteById
consultas derivadas findByCorreo,  deleteMail,  se generan de forma automatica

 */