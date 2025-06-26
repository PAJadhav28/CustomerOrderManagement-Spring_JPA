package com.example.service;

import com.example.dao.ReportDAO;
import com.example.model.ReportEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportDAO reportDAO;

    @Autowired
    public ReportService(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public List<ReportEntry> generateUSASpendingReport() {
        return reportDAO.generateUSASpendingReport();
    }
}