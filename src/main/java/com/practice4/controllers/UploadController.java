package com.practice4.controllers;

import com.practice4.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@Controller
public class UploadController {

    @Autowired
    private DataService dataservice;

    @GetMapping("/upload")
    public String uploadPage(){
        return "upload";
    }

    @GetMapping("/display")
    public String displayPage(Model model){
        model.addAttribute("dataItems", dataservice.getData());
        return "display";
    }
}
