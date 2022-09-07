package com.david.managementappv2.vehicle.controllers;


import com.david.managementappv2.vehicle.models.VehicleModel;
import com.david.managementappv2.vehicle.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    //Get All VehicleModels
    @GetMapping("vehicle/vehicleModels")
    public String findAll(Model model){
        model.addAttribute("vehicleModels", vehicleModelService.findAll());
        return "/vehicle/vehicleModels";
    }

    @RequestMapping("/vehicle/vehicleModel/{id}")
    @ResponseBody
    public Optional<VehicleModel> findById(@PathVariable Integer id)
    {
        return vehicleModelService.findById(id);
    }

    //Add VehicleModel
    @PostMapping(value="/vehicle/vehicleModels")
    public String addNew(VehicleModel vehicleModel) {
        vehicleModelService.save(vehicleModel);
        return "redirect:/vehicle/vehicleModels";
    }

    @RequestMapping(value="vehicleModel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleModelService.delete(id);
        return "redirect:/vehicle/vehicleModels";
    }
}