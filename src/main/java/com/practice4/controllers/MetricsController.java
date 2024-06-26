package com.practice4.controllers;

import com.practice4.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MetricsController {


    private final DataService dataService;

    public MetricsController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping(value = "/metrics", method = RequestMethod.GET)
    public String getMetricsPage(){
        return "metrics";
    }



    @GetMapping("/api/metrics/reviewer-jobs")
    @ResponseBody
    public Map<String, Long> getReviewerJobs(){
        return dataService.getJobsByReviewer();
    }

    @GetMapping("/api/metrics/pending-correction")
    @ResponseBody
    public long getPendingCorrectionCount(){
        return dataService.countPendingCorrection();
    }

    @RequestMapping(value = "/api/metrics/status-counts", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Long> getStatusCounts(){
        return dataService.getStatusCounts();
    }

    @GetMapping("/api/metrics/go-counts")
    @ResponseBody
    public Map<String, Long> getGoCounts(){
        return dataService.getGOCounts();
    }



}
