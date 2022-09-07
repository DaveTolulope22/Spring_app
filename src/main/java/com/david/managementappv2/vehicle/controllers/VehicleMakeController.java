package com.david.managementappv2.vehicle.controllers;

import com.david.managementappv2.vehicle.models.VehicleMake;
import com.david.managementappv2.vehicle.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleMakeController {

    @Autowired
    private VehicleMakeService vehicleMakeService;

    //Get All VehicleMakes
    @GetMapping("vehicle/vehicleMakes")
    public String findAll(Model model){
        model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
        return "/vehicle/vehicleMakes";
    }

    @RequestMapping("/vehicle/vehicleMakes/{id}")
    @ResponseBody
    public Optional<VehicleMake> findById(@PathVariable Integer id)
    {
        return vehicleMakeService.findById(id);
    }

    //Add VehicleMake
    @PostMapping(value="/vehicle/vehicleMakes")
    public String addNew(VehicleMake vehicleMake) {
        vehicleMakeService.save(vehicleMake);
        return "redirect:/vehicle/vehicleMakes";
    }

    @RequestMapping(value="vehicleMake/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleMakeService.delete(id);
        return "redirect:/vehicle/vehicleMakes";
    }
}