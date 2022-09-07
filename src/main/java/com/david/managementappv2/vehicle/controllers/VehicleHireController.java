package com.david.managementappv2.vehicle.controllers;


import com.david.managementappv2.settings.services.ClientService;
import com.david.managementappv2.settings.services.LocationService;
import com.david.managementappv2.vehicle.models.VehicleHire;
import com.david.managementappv2.vehicle.services.VehicleHireService;
import com.david.managementappv2.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleHireController {

    @Autowired
    private VehicleHireService vehicleHireService;
    @Autowired private ClientService clientService;
    @Autowired private LocationService locationService;
    @Autowired private VehicleService vehicleService;

    public Model addModelAttributes(Model model){
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("vehicles", vehicleService.findAll());
        return model;
    }

    //Get All VehicleHires
    @GetMapping("/vehicle/hires")
    public String findAll(Model model){
        model.addAttribute("hires", vehicleHireService.findAll());
        return "/vehicle/hires";
    }

    @GetMapping("/vehicle/hireAdd")
    public String addHire(Model model){
        addModelAttributes(model);
        return "/vehicle/hireAdd";
    }

    @GetMapping("/vehicle/hire/{op}/{id}")
    public String editHire(Model model, @PathVariable Integer id, @PathVariable String op){
        VehicleHire hire = vehicleHireService.findById(id);
        model.addAttribute("hire", hire);
        addModelAttributes(model);
        return "/vehicle/hire"+op;
    }

    //Add VehicleHire
    @PostMapping("/vehicle/hires")
    public String addNew(VehicleHire vehicleHire) {
        vehicleHireService.save(vehicleHire);
        return "redirect:/vehicle/hires";
    }

    @RequestMapping(value="vehicle/hire/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleHireService.delete(id);
        return "redirect:/vehicle/hires";
    }

}