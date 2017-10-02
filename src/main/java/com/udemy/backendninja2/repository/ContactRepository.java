package com.udemy.backendninja2.repository;

import com.udemy.backendninja2.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("contactRepository")
public interface ContactRepository  extends JpaRepository<Contact, Serializable>{

    public abstract Contact findById(int id);
}
