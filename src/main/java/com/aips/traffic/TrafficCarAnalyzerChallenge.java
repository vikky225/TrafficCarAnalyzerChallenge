package com.aips.traffic;

import com.aips.traffic.domain.TrafficRecord;
import com.aips.traffic.output.ReportGenerator;
import com.aips.traffic.service.TrafficAnalyzerService;
import com.aips.traffic.service.TrafficDataParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrafficAnalyzer {

    private static final Logger LOGGER = Logger.getLogger(TrafficAnalyzer.class.getName());

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                printUsage();
                System.exit(1);
            }

            Path inputFile = Path.of(args[0]);

            TrafficDataParser parser = new TrafficDataParser();
            List<TrafficRecord> records = parser.parseFile(inputFile);

            if (records.isEmpty()) {
                System.err.println("No valid traffic records found in file: " + inputFile);
                System.exit(1);
            }

            LOGGER.info(() -> String.format("Successfully parsed %d records", records.size()));

            TrafficAnalyzerService analyzer = new TrafficAnalyzerService(records);

            int totalCars = analyzer.calculateTotalCars();
            var dailyTotals = analyzer.calculateDailyTotals();
            var topPeriods = analyzer.findTopPeriods(3);
            var leastBusyPeriod = analyzer.findLeastBusyPeriod(3);

            ReportGenerator reportGenerator = new ReportGenerator();
            String report = reportGenerator.generateSimpleOutput(
                    totalCars, dailyTotals, topPeriods, leastBusyPeriod
            );

            System.out.println(report);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Unexpected error", e);
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("""
            Traffic Data Analyzer
            =====================
            
            Usage: java TrafficAnalyzer <input-file>
            
            Example:
              java TrafficAnalyzer traffic_data.txt
            
            Input file format (ISO 8601 timestamp + car count):
              2024-01-01T00:00:00 5
              2024-01-01T00:30:00 10
              ...
            """);
    }
}