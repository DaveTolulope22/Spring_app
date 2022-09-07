package com.david.managementappv2.vehicle.controllers;

import com.david.managementappv2.hr.services.EmployeeService;
import com.david.managementappv2.settings.services.LocationService;
import com.david.managementappv2.vehicle.models.Vehicle;
import com.david.managementappv2.vehicle.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired private VehicleTypeService vehicleTypeService;
    @Autowired private VehicleMakeService vehicleMakeService;
    @Autowired private VehicleModelService vehicleModelService;
    @Autowired private LocationService locationService;
    @Autowired private EmployeeService employeeService ;
    @Autowired private VehicleStatusService vehicleStatusService;

    public Model addModelAttributes(Model model){
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
        model.addAttribute("vehicleModels", vehicleModelService.findAll());
        model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());
        return model;
    }

    //Get All Vehicles
    @GetMapping("/vehicle/vehicles")
    public String findAll(Model model){
        addModelAttributes(model);
        return "/vehicle/vehicles";
    }

    @GetMapping("/vehicle/vehicleAdd")
    public String addVehicle(Model model){
        addModelAttributes(model);
        return "vehicle/vehicleAdd";
    }

    //The op settings is either Edit or Details
    @GetMapping("/vehicle/vehicle/{op}/{id}")
    public String editVehicle(@PathVariable Integer id, @PathVariable String op, Model model){
        Vehicle vehicle = vehicleService.findById(id);
        model.addAttribute("vehicle", vehicle);
        addModelAttributes(model);
        return "/vehicle/vehicle"+ op; //returns vehicleEdit or vehicleDetails
    }

    //Add Vehicle
    @PostMapping("/vehicle/vehicles")
    public String addNew(Vehicle vehicle) {
        vehicleService.save(vehicle);
        return "redirect:/vehicle/vehicles";
    }

    @RequestMapping(value="/vehicle/vehicle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleService.delete(id);
        return "redirect:/vehicle/vehicles";
    }
}