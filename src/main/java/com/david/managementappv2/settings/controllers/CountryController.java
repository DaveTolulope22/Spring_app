package com.david.managementappv2.settings.controllers;

import com.david.managementappv2.settings.models.Country;
import com.david.managementappv2.settings.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class CountryController {
    /*
    //autowiring the business service into the controller
    @Autowired
    private CountryService countryService;
    //writing a method to return list of countries from the service
    //@GetMapping ensures that if the user specify /countries its going to direct the users there
    @GetMapping("/countries")
    // @ResponseBody returning a jason body
    //@ResponseBody
    // This model is helping to also pass data to the uI
    public String getAll(Model model){
         List<Country> countries = countryService.findAll();
         model.addAttribute("countries", countries);
       //displaying the html table template
        return "settings/countryList";
    }

    //new method to get add page html
    @GetMapping("/countryAdd")
    public String addCountry(){return "settings/countryAdd";}

    //The op parameter is either Edit or Details
    @GetMapping("/countryEdit/{id}")
    public String editCountry(@PathVariable Integer id, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "settings/countryEdit";
    }

    @GetMapping("/countryDetails/{id}")
    public String detailsCountry(@PathVariable Integer id, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "settings/countryDetails";
    }



    @PostMapping("/countries")
    public String save(Country country){
        countryService.save(country);
        //This how we refresh our list
        return "redirect:/countries";
    }

     //delete method
     @RequestMapping(value = "/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
     public String delete(@PathVariable Integer id){
         countryService.delete(id);
         return "redirect:/countries";
    }
    //UPDATE MEHTOD
    @RequestMapping(value = "/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Country country){
        countryService.save(country);
        return "redirect:/countries";
    }*/
    @Autowired
    private CountryService countryService;

//    @GetMapping("/parameters/countries")
//    public String  getAll(Model model, String keyword){
//        List<Country> countries;
//        countries = keyword == null? countryService.findAll():countryService.findByKeyword(keyword);
//        model.addAttribute("countries", countries);
//        return "/parameters/countries";
//    }

    @GetMapping("/settings/countries")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/settings/countries/page/{pageNumber}")
    public String  getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Country> page = countryService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Country> countries = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("countries", countries);

        return "/settings/countries";
    }

    @GetMapping("/settings/countries/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir) {

        Page<Country> page = countryService.findAllWithSort(field, sortDir, currentPage);
        List<Country> countries = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("countries", countries);
        return "/settings/countries";
    }

    //The Get Country By Id
    @GetMapping("/settings/country/{id}")
    @ResponseBody
    public Country getCountry(@PathVariable Integer id){
        return countryService.getById(id);
    }

    @GetMapping("/settings/countryAdd")
    public String addCountry(){
        return "settings/countryAdd";
    }




    //The op settings is either Edit or Details
    @GetMapping("/settings/country/{op}/{id}")
    public String editCountry(@PathVariable Integer id, @PathVariable String op, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "/settings/country"+ op;
    }

    @PostMapping("/settings/countries")
    public String save(Country country){
        countryService.save(country);
        return "redirect:/settings/countries";
    }

    @RequestMapping(value = "/settings/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public  String delete(@PathVariable Integer id){
        countryService.delete(id);
        return "redirect:/settings/countries";
    }






























}
