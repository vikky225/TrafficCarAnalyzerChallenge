package com.aips.traffic.service;

import com.aips.traffic.domain.TrafficRecord;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class TrafficAnalyzerService {

    private final List<TrafficRecord> records;

    public TrafficAnalyzerService(List<TrafficRecord> records) {
        if (records == null || records.isEmpty()) {
            throw new IllegalArgumentException("Records cannot be null or empty");
        }

        // Defensive copy + sort by timestamp
        this.records = records.stream()
                .sorted(Comparator.comparing(TrafficRecord::timestamp))
                .collect(Collectors.toList());
    }

    public int calculateTotalCars() {
        return records.stream()
                .mapToInt(TrafficRecord::carCount)
                .sum();
    }

    public Map<java.time.LocalDate, Integer> calculateDailyTotals() {
        return records.stream()
                .collect(Collectors.groupingBy(
                        r -> r.timestamp().toLocalDate(),
                        Collectors.summingInt(TrafficRecord::carCount)
                ));
    }

    public List<TrafficRecord> findTopPeriods(int topN) {
        return records.stream()
                .sorted(Comparator.comparingInt(TrafficRecord::carCount).reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    /**
     * Finds the least busy contiguous window of half-hour records.
     */
    public List<TrafficRecord> findLeastBusyPeriod(int windowSize) {
        if (windowSize <= 0) {
            throw new IllegalArgumentException("Window size must be positive");
        }

        if (records.size() < windowSize) {
            throw new IllegalArgumentException("Not enough records for window size");
        }

        int minSum = Integer.MAX_VALUE;
        int bestStartIndex = -1;

        for (int i = 0; i <= records.size() - windowSize; i++) {

            // Validate contiguous 30-minute intervals
            boolean contiguous = true;
            int sum = records.get(i).carCount();

            for (int j = 1; j < windowSize; j++) {
                var prev = records.get(i + j - 1).timestamp();
                var curr = records.get(i + j).timestamp();

                if (Duration.between(prev, curr).toMinutes() != 30) {
                    contiguous = false;
                    break;
                }

                sum += records.get(i + j).carCount();
            }

            if (contiguous && sum < minSum) {
                minSum = sum;
                bestStartIndex = i;
            }
        }

        if (bestStartIndex == -1) {
            throw new IllegalStateException("No contiguous half-hour window found");
        }

        return records.subList(bestStartIndex, bestStartIndex + windowSize);
    }
}
