package com.david.managementappv2.vehicle.controllers;

import com.david.managementappv2.settings.services.LocationService;
import com.david.managementappv2.vehicle.models.VehicleMovement;
import com.david.managementappv2.vehicle.services.VehicleMovementService;
import com.david.managementappv2.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleMovementController {

    @Autowired
    private VehicleMovementService vehicleMovementService;
    @Autowired private LocationService locationService;
    @Autowired private VehicleService vehicleService;

    public Model addModelAttributes(Model model){
        model.addAttribute("locations1", locationService.findAll());
        model.addAttribute("locations2", locationService.findAll());
        model.addAttribute("vehicles", vehicleService.findAll());
        return  model;
    }
    //Get All VehicleMovements
    @GetMapping("vehicle/movements")
    public String findAll(Model model){
        model.addAttribute("movements", vehicleMovementService.findAll());
        return "vehicle/movements";
    }

    @GetMapping("/vehicle/movementAdd")
    public String addMovement(Model model){
        addModelAttributes(model);
        return "/vehicle/movementAdd";
    }

    @GetMapping("/vehicle/movement/{op}/{id}")
    public String editMovement(Model model, @PathVariable Integer id, @PathVariable String op){
        VehicleMovement movement = vehicleMovementService.findById(id);
        model.addAttribute("movement", movement);
        addModelAttributes(model);
        return "/vehicle/movement"+op;
    }

    //Add VehicleMovement
    @PostMapping("/vehicle/movements")
    public String addNew(VehicleMovement vehicleMovement) {
        vehicleMovementService.save(vehicleMovement);
        return "redirect:/vehicle/movements";
    }

    @RequestMapping(value="/vehicle/movements/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleMovementService.delete(id);
        return "redirect:/vehicle/movements";
    }


}