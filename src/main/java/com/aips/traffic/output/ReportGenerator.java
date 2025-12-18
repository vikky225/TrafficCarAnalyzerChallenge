package com.aips.traffic.output;

import com.aips.traffic.domain.DailyTotal;
import com.aips.traffic.domain.TrafficRecord;

import java.util.List;

public class ReportGenerator {

    public String generateSimpleOutput(int totalCars, List<DailyTotal> dailyTotals, List<TrafficRecord> topPeriods, List<TrafficRecord> leastBusyPeriod) {
        StringBuilder report = new StringBuilder();

        report.append("Total cars: ").append(totalCars).append("\n\n");

        report.append("Daily totals:\n");
        dailyTotals.forEach(dailyTotal -> report.append(dailyTotal.getDate()).append(" ").append(dailyTotal.getTotalCars()).append("\n"));

        report.append("\nTop 3 half-hours:\n");
        topPeriods.forEach(record -> report.append(record.timestamp()).append(" ").append(record.carCount()).append("\n"));

        report.append("\nLeast busy 1.5-hour period:\n");
        leastBusyPeriod.forEach(record -> report.append(record.timestamp()).append(" ").append(record.carCount()).append("\n"));

        return report.toString();
    }
}