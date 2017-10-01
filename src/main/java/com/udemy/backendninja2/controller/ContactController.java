package com.udemy.backendninja2.controller;

import com.udemy.backendninja2.constant.ViewConstant;
import com.udemy.backendninja2.model.ContactModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return ViewConstant.CONTACTS;
    }

    @GetMapping("/contactform")
    private String redirectContactForm(Model model){
        //Como vamos a gestionar un Modelo, lo añadimos de manera vacía
        model.addAttribute("contactModel", new ContactModel());
        return ViewConstant.CONTACT_FORM;
    }

    @PostMapping("/addcontact")
    //El name="contactModel" se corresponde con el objeto en el HTML 'contactModel'
    // y el ContactModel contactModel se corresponde con Java
    private String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
                              Model model){
        LOG.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
        model.addAttribute("result", 1); //"Se añadió correctamente"
        return ViewConstant.CONTACTS;
    }


}
