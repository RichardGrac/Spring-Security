package com.udemy.backendninja2.service.impl;

import com.udemy.backendninja2.component.ContactConverter;
import com.udemy.backendninja2.entity.Contact;
import com.udemy.backendninja2.model.ContactModel;
import com.udemy.backendninja2.repository.ContactRepository;
import com.udemy.backendninja2.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{

    @Autowired
    @Qualifier("contactRepository")
    private ContactRepository contactRepository;

    @Autowired
    @Qualifier("contactConverter")
    private ContactConverter contactConverter;

    @Override
    public ContactModel addContact(ContactModel contactModel) {
        System.out.println("ContactModel: " + contactModel.toString());
        Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
        // Regresamos el contacto en forma de "Modelo" para el Controller
        return contactConverter.convertContact2ContactModel(contact);
    }
}
