package com.udemy.backendninja2.service.impl;

import com.udemy.backendninja2.converter.ContactConverter;
import com.udemy.backendninja2.entity.Contact;
import com.udemy.backendninja2.model.ContactModel;
import com.udemy.backendninja2.repository.ContactRepository;
import com.udemy.backendninja2.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ContactModel> listAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactModel> contactsModel = new ArrayList<>();
        for (Contact contact: contacts){
            contactsModel.add(contactConverter.convertContact2ContactModel(contact));
        }
        return contactsModel;
    }

    @Override
    public Contact findContactById(int id) {
        return contactRepository.findById(id);
    }

    public ContactModel findContactByIdModel(int id){
        return contactConverter.convertContact2ContactModel(findContactById(id));
    }

    @Override
    public void removeContact(int id) {
        Contact contact = findContactById(id);
        if (contact!=null){ // Lo encontró en la BD
            contactRepository.delete(contact);
        }
    }
}
