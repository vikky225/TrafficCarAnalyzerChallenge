package com.aips.traffic.service;

import com.aips.traffic.domain.TrafficRecord;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficDataParserTest {

    @Test
    void parseFile_ValidFile_ReturnsCorrectRecords() throws Exception {
        // Arrange
        Path filePath = Path.of("src/test/resources/sample_traffic_data.txt");
        TrafficDataParser parser = new TrafficDataParser();

        // Act
        List<TrafficRecord> records = parser.parseFile(filePath);

        // Assert
        assertThat(records).isNotEmpty();
        assertThat(records).extracting("timestamp").contains(LocalDateTime.parse("2021-12-01T05:00:00"));
        assertThat(records).extracting("carCount").contains(5);
    }

    @Test
    void parseLine_ValidLine_ReturnsTrafficRecord() {
        // Arrange
        String line = "2021-12-01T05:00:00 5";
        TrafficDataParser parser = new TrafficDataParser();

        // Act
        TrafficRecord record = parser.parseLine(line);

        // Assert
        assertThat(record).isNotNull();
        assertThat(record.timestamp()).isEqualTo(LocalDateTime.parse("2021-12-01T05:00:00"));
        assertThat(record.carCount()).isEqualTo(5);
    }

    @Test
    void parseLine_InvalidLine_ReturnsNull() {
        // Arrange
        String line = "invalid line";
        TrafficDataParser parser = new TrafficDataParser();

        // Act
        TrafficRecord record = parser.parseLine(line);

        // Assert
        assertThat(record).isNull();
    }
}