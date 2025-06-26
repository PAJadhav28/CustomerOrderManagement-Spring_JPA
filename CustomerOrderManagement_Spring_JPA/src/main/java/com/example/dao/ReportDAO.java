package com.example.dao;

import com.example.model.ReportEntry;
import com.example.util.EntityManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportDAO {

    private final EntityManagerUtil emUtil;

    @Autowired
    public ReportDAO(EntityManagerUtil emUtil) {
        this.emUtil = emUtil;
    }

    public List<ReportEntry> generateUSASpendingReport() {
        EntityManager em = emUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("{CALL GenerateUSASpendingReport()}");
            query.executeUpdate();
            em.getTransaction().commit();

            Query reportQuery = em.createNativeQuery("SELECT * FROM customer_spending_report");
            List<Object[]> results = reportQuery.getResultList();
            List<ReportEntry> report = new ArrayList<>();
            for (Object[] row : results) {
                report.add(new ReportEntry(
                    (Integer) row[0],
                    (Integer) row[1],
                    (String) row[2],
                    ((BigDecimal) row[3]).doubleValue(),
                    (Timestamp) row[4]
                ));
            }
            return report;
        } finally {
            em.close();
        }
    }
}