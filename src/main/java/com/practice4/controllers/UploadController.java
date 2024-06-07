package com.practice4.controllers;

import com.practice4.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

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


    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    /*@PostMapping("/")
    public String handleReDirect(){
        return "redirect:/upload";
    }*/

}
