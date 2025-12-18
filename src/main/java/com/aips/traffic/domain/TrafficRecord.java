package com.aips.traffic.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class TrafficRecord {
    private final LocalDateTime timestamp;
    private final int carCount;

    public TrafficRecord(LocalDateTime timestamp, int carCount) {
        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
        if (carCount < 0) {
            throw new IllegalArgumentException("Car count cannot be negative");
        }
        this.timestamp = timestamp;
        this.carCount = carCount;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public int carCount() {
        return carCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficRecord that = (TrafficRecord) o;
        return carCount == that.carCount && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, carCount);
    }

    @Override
    public String toString() {
        return "TrafficRecord{" +
                "timestamp=" + timestamp +
                ", carCount=" + carCount +
                '}';
    }
}