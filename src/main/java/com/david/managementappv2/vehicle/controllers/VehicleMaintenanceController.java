package com.david.managementappv2.vehicle.controllers;


import com.david.managementappv2.settings.services.SupplierService;
import com.david.managementappv2.vehicle.models.VehicleMaintenance;
import com.david.managementappv2.vehicle.services.VehicleMaintenanceService;
import com.david.managementappv2.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleMaintenanceController {

    @Autowired
    private VehicleMaintenanceService vehicleMaintenanceService;
    @Autowired private VehicleService vehicleService;
    @Autowired private SupplierService supplierService;

    public Model addModelAttributes(Model model){
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return model;
    }

    //Get All VehicleMaintenances
    @GetMapping("/vehicle/maintenances")
    public String findAll(Model model){
        model.addAttribute("maintenances", vehicleMaintenanceService.findAll());
        return "/vehicle/maintenances";
    }

    @GetMapping("/vehicle/maintenanceAdd")
    public String addMaintenance(Model model){
        addModelAttributes(model);
        return "/vehicle/maintenanceAdd";
    }

    @GetMapping("/vehicle/maintenance/{op}/{id}")
    public String editMaintenance(Model model, @PathVariable Integer id, @PathVariable String op){
        VehicleMaintenance maintenance = vehicleMaintenanceService.findById(id);
        model.addAttribute("maintenance", maintenance);
        addModelAttributes(model);
        return "/vehicle/maintenance"+op;
    }

    //Add VehicleMaintenance
    @PostMapping("/vehicle/maintenances")
    public String addNew(VehicleMaintenance vehicleMaintenance) {
        vehicleMaintenanceService.save(vehicleMaintenance);
        return "redirect:/vehicle/maintenances";
    }

    @RequestMapping(value="vehicle/maintenance/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleMaintenanceService.delete(id);
        return "redirect:/vehicle/maintenances";
    }

}