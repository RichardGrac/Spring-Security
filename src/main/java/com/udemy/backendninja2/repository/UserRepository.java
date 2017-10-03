package com.udemy.backendninja2.repository;

import com.udemy.backendninja2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {
    /* Para JpaRepository usaremos la entidad User.
        Crearemos un método para buscar un usuario por username
    */

    public abstract User findByUsername(String username);
}
