package com.practice4.controllers;

import com.practice4.models.DataItem;
import com.practice4.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ArrayList;


@Controller
public class UploadController {

    @Autowired
    private DataService dataservice;

    @GetMapping("/upload")
    public String uploadPage(){
        return "upload";
    }
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        dataservice.uploadData(file);
        return "redirect:/display";
    }

    @GetMapping("/display")
    public String displayPage(Model model){
        model.addAttribute("dataItems", dataservice.getData());
        return "display";
    }
    @PostMapping("/display")
    public String updateDisplayPage(@RequestParam("columns") List<String> columns, Model model){
        model.addAttribute("dataItems", dataservice.getData());
        model.addAttribute("selectedColumns", columns);
        return "display";
    }

    @PostMapping("/filter")
    public String filterDisplayPage(
            @RequestParam("column1Filter") String column1Filter,
            @RequestParam("column2Filter") String column2Filter,
            @RequestParam("column3Filter") String column3Filter,
            @RequestParam("column4Filter") String column4Filter,
            @RequestParam("column5Filter") String column5Filter,
            Model model) {
        List<DataItem> filteredData = dataservice.filterData(column1Filter, column2Filter, column3Filter, column4Filter, column5Filter);
        model.addAttribute("dataItems", filteredData);
        model.addAttribute("selectedColumns", List.of("column1", "column2", "column3", "column4", "column5"));
        return "display";
    }

    @PostMapping("/clear")
    public String clearData(){
        dataservice.clearData();
        return "redirect:/upload";
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    /*@PostMapping("/")
    public String handleReDirect(){
        return "redirect:/upload";
    }*/

}
