package com.david.managementappv2.vehicle.controllers;

import com.david.managementappv2.vehicle.models.VehicleStatus;
import com.david.managementappv2.vehicle.services.VehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleStatusController {

    @Autowired
    private VehicleStatusService vehicleStatusService;

    //Get All VehicleStatuss
    @GetMapping("/vehicle/vehicleStatuses")
    public String findAll(Model model){
        model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());
        return "/vehicle/vehicleStatuses";
    }

    @RequestMapping("/vehicle/vehicleStatus/{id}")
    @ResponseBody
    public Optional<VehicleStatus> findById(@PathVariable Integer id)
    {
        return vehicleStatusService.findById(id);
    }

    //Add VehicleStatus
    @PostMapping(value="/vehicle/vehicleStatuses")
    public String addNew(VehicleStatus vehicleStatus) {
        vehicleStatusService.save(vehicleStatus);
        return "redirect:/vehicle/vehicleStatuses";
    }

    @RequestMapping(value="vehicle/vehicleStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleStatusService.delete(id);
        return "redirect:/vehicle/vehicleStatuses";
    }
}