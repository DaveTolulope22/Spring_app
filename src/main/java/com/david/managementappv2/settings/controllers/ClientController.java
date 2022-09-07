package com.david.managementappv2.settings.controllers;

import com.david.managementappv2.settings.models.Client;
import com.david.managementappv2.settings.services.ClientService;
import com.david.managementappv2.settings.services.CountryService;
import com.david.managementappv2.settings.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired	private CountryService countryService;
    @Autowired	private StateService stateService;

    public Model addModelAttributes(Model model){
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        return model;
    }

    @GetMapping("/settings/clients")
    public String findAll(Model model){
        addModelAttributes(model);
        return "/settings/clients";
    }

    @GetMapping("/settings/clientAdd")
    public String addClient(Model model){
        model.addAttribute("countries", countryService.findAll());
        return "settings/clientAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/settings/client/{op}/{id}")
    public String editClient(@PathVariable Integer id, @PathVariable String op, Model model){
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        addModelAttributes(model);
        return "/settings/client"+ op; //returns clientEdit or clientDetails
    }

    @PostMapping("/settings/clients")
    public String save(Client client) {
        clientService.save(client);
        return "redirect:/settings/clients";
    }

    @RequestMapping(value="/settings/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Integer id) {
        clientService.deleteById(id);
        return "redirect:/settings/clients";
    }

}