package com.udemy.backendninja2.service;

import com.udemy.backendninja2.entity.Contact;
import com.udemy.backendninja2.model.ContactModel;

import java.util.List;

public interface ContactService {
    public abstract ContactModel addContact(ContactModel contactModel);
    public abstract List<ContactModel> listAllContacts();
    public abstract Contact findContactById(int id);
    public abstract void removeContact(int id);
    public abstract ContactModel findContactByIdModel(int id);
}
