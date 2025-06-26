package com.example.controller;

import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String generateReport(Model model) {
        try {
            model.addAttribute("report", reportService.generateUSASpendingReport());
            return "report";
        } catch (Exception e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "error";
        }
    }
}