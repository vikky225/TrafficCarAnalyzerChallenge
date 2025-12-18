package com.aips.traffic.service;

import com.aips.traffic.domain.TrafficRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficAnalyzerServiceTest {

    @Test
    void calculateTotalCars_ValidRecords_ReturnsCorrectTotal() {
        // Arrange
        List<TrafficRecord> records = List.of(
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:00:00"), 5),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:30:00"), 10)
        );
        TrafficAnalyzerService service = new TrafficAnalyzerService(records);

        // Act
        int totalCars = service.calculateTotalCars();

        // Assert
        assertThat(totalCars).isEqualTo(15);
    }

    @Test
    void calculateDailyTotals_ValidRecords_ReturnsCorrectMap() {
        // Arrange
        List<TrafficRecord> records = List.of(
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:00:00"), 5),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:30:00"), 10),
                new TrafficRecord(LocalDateTime.parse("2021-12-02T00:00:00"), 20)
        );
        TrafficAnalyzerService service = new TrafficAnalyzerService(records);

        // Act
        Map<LocalDate, Integer> dailyTotals = service.calculateDailyTotals();

        // Assert
        assertThat(dailyTotals).containsEntry(LocalDate.parse("2021-12-01"), 15);
        assertThat(dailyTotals).containsEntry(LocalDate.parse("2021-12-02"), 20);
    }

    @Test
    void findTopPeriods_ValidRecords_ReturnsTopNRecords() {
        // Arrange
        List<TrafficRecord> records = List.of(
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:00:00"), 5),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:30:00"), 10),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T01:00:00"), 20)
        );
        TrafficAnalyzerService service = new TrafficAnalyzerService(records);

        // Act
        List<TrafficRecord> topPeriods = service.findTopPeriods(2);

        // Assert
        assertThat(topPeriods).containsExactly(
                new TrafficRecord(LocalDateTime.parse("2021-12-01T01:00:00"), 20),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:30:00"), 10)
        );
    }

    @Test
    void findLeastBusyPeriod_ValidRecords_ReturnsCorrectPeriod() {
        // Arrange
        List<TrafficRecord> records = List.of(
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:00:00"), 5),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:30:00"), 10),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T01:00:00"), 20),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T01:30:00"), 1)
        );
        TrafficAnalyzerService service = new TrafficAnalyzerService(records);

        // Act
        List<TrafficRecord> result = service.findLeastBusyPeriod(3);

        // Assert — LOWEST total (31), not first window
        assertThat(result).containsExactly(
                new TrafficRecord(LocalDateTime.parse("2021-12-01T00:30:00"), 10),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T01:00:00"), 20),
                new TrafficRecord(LocalDateTime.parse("2021-12-01T01:30:00"), 1)
        );
    }
}