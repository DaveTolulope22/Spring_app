package com.david.managementappv2.vehicle.controllers;

import com.david.managementappv2.vehicle.models.VehicleType;
import com.david.managementappv2.vehicle.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    //Get All VehicleTypes
    @GetMapping("/vehicle/vehicleTypes")
    public String findAll(Model model){
        model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
        return "/vehicle/vehicleTypes";
    }

    @RequestMapping("/vehicle/vehicleType/{id}")
    @ResponseBody
    public Optional<VehicleType> findById(@PathVariable Integer id)
    {
        return vehicleTypeService.findById(id);
    }

    //Add VehicleType
    @PostMapping(value="/vehicle/vehicleTypes")
    public String addNew(VehicleType vehicleType) {
        vehicleTypeService.save(vehicleType);
        return "redirect:/vehicle/vehicleTypes";
    }

    @RequestMapping(value="vehicle/vehicleType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleTypeService.delete(id);
        return "redirect:/vehicle/vehicleTypes";
    }

}