package com.udemy.backendninja2.controller;

import com.udemy.backendninja2.constant.ViewConstant;
import com.udemy.backendninja2.entity.Contact;
import com.udemy.backendninja2.model.ContactModel;
import com.udemy.backendninja2.service.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    @Autowired
    @Qualifier("contactServiceImpl")
    private ContactService contactService;

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/contacts/showcontacts";
    }

    @GetMapping("/contactform")
    private String redirectContactForm(@RequestParam(name = "id", required = false) int id, Model model){
        ContactModel contactModel = new ContactModel();
        if (id != 0){ //Si venimos de presiónar el Boton de Editar, buscamos por Id:
            contactModel = contactService.findContactByIdModel(id);
        }
        /* Ver bien que: si se va a Editar un registro, ya fué buscado y está en "contactModel", sino si se va a
          agregar uno nuevo ("addContact"), entonces el ContactModel ya está inicializado de forma vacía.
          Agregamos: */
        model.addAttribute("contactModel", contactModel);
        return ViewConstant.CONTACT_FORM;
    }

    @PostMapping("/addcontact")
    //El name="contactModel" se corresponde con el objeto en el HTML 'contactModel'
    // y el ContactModel contactModel se corresponde con Java
    private String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
                              Model model){
        LOG.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
        // Lo añadimos:
        if(contactService.addContact(contactModel) != null){
            model.addAttribute("result", 1); //"Se añadió correctamente"
        }else{
            model.addAttribute("result", 0); //"No se añadió"
        }
        return "redirect:/contacts/showcontacts";
    }

    @GetMapping("/showcontacts")
    public ModelAndView showContacts(){
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
        // En el each del HTML se recorrerá el "contacts"
        mav.addObject("contacts", contactService.listAllContacts());
        return mav;
    }

    @GetMapping("/removecontact")
    public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id){
        contactService.removeContact(id);
        return showContacts();
    }
}
