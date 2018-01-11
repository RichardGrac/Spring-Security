package com.udemy.backendninja2.controller;

import com.udemy.backendninja2.model.ContactModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/* Controlador REST */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    /* Este objeto devolverá un JSON: ResponseEntity*/
    @GetMapping("/checkrest")
    public ResponseEntity<ArrayList<ContactModel>> checkRest(){
        ContactModel contactModel = new ContactModel(2, "Richard", "Grac", "4491234567", "Aguascalientes");
        ContactModel contactModel2 = new ContactModel(3, "LAVS", "0795", "4497654321", "Aguascalientes");
        ArrayList<ContactModel> contactModels = new ArrayList<>();
        contactModels.add(contactModel);
        contactModels.add(contactModel2);
        return new ResponseEntity<ArrayList<ContactModel>>(contactModels, HttpStatus.OK);
    }
}
