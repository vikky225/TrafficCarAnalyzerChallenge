package com.aips.traffic.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TrafficRecordTest {

    @Test
    void constructor_ValidArguments_CreatesTrafficRecord() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.parse("2021-12-01T00:00:00");
        int carCount = 5;

        // Act
        TrafficRecord record = new TrafficRecord(timestamp, carCount);

        // Assert
        assertThat(record.timestamp()).isEqualTo(timestamp);
        assertThat(record.carCount()).isEqualTo(carCount);
    }

    @Test
    void constructor_NullTimestamp_ThrowsException() {
        // Arrange
        int carCount = 5;

        // Act & Assert
        assertThatThrownBy(() -> new TrafficRecord(null, carCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Timestamp cannot be null");
    }

    @Test
    void constructor_NegativeCarCount_ThrowsException() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.parse("2021-12-01T00:00:00");

        // Act & Assert
        assertThatThrownBy(() -> new TrafficRecord(timestamp, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car count cannot be negative");
    }

    @Test
    void equals_SameValues_ReturnsTrue() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.parse("2021-12-01T00:00:00");
        TrafficRecord record1 = new TrafficRecord(timestamp, 5);
        TrafficRecord record2 = new TrafficRecord(timestamp, 5);

        // Act & Assert
        assertThat(record1).isEqualTo(record2);
    }
}