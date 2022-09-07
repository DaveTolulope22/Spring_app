package com.david.managementappv2.settings.controllers;

import com.david.managementappv2.settings.models.Supplier;
import com.david.managementappv2.settings.repositories.SupplierRepository;
import com.david.managementappv2.settings.services.CountryService;
import com.david.managementappv2.settings.services.StateService;
import com.david.managementappv2.settings.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class SupplierController {

    @Autowired	private SupplierService supplierService;
    @Autowired	private CountryService countryService;
    @Autowired	private StateService stateService;

    public Model addModelAttributes(Model model){
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        return model;
    }

    @GetMapping("/settings/suppliers")
    public String findAll(Model model){
        addModelAttributes(model);
        return "/settings/suppliers";
    }

    @GetMapping("/settings/supplierAdd")
    public String addSupplier(Model model){
        model.addAttribute("countries", countryService.findAll());
        return "settings/supplierAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/settings/supplier/{op}/{id}")
    public String editSupplier(@PathVariable Integer id, @PathVariable String op, Model model){
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        addModelAttributes(model);
        return "/settings/supplier"+ op; //returns supplierEdit or supplierDetails
    }

    @PostMapping("/settings/suppliers")
    public String save(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/settings/suppliers";
    }

    @RequestMapping(value="/settings/suppliers/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return "redirect:/settings/suppliers";
    }

}