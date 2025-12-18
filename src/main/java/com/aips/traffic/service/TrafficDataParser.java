package com.aips.traffic.service;

import com.aips.traffic.domain.TrafficRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrafficDataParser {

  private static final Logger LOGGER = Logger.getLogger(TrafficDataParser.class.getName());

  public List<TrafficRecord> parseFile(Path filePath) throws IOException {
    List<TrafficRecord> records = new ArrayList<>();

    Files.lines(filePath).forEach(line -> {
      try {
        TrafficRecord record = parseLine(line);
        if (record != null) {
          records.add(record);
        }
      } catch (Exception e) {
        LOGGER.log(Level.WARNING, "Failed to parse line: {0}. Error: {1}", new Object[]{line, e.getMessage()});
      }
    });

    return records;
  }

  public TrafficRecord parseLine(String line) {
    if (line == null || line.isBlank()) {
      LOGGER.warning("Skipped empty or blank line");
      return null;
    }

    String[] parts = line.split("\\s+");
    if (parts.length != 2) {
      LOGGER.warning("Invalid line format, expected 2 parts but got " + parts.length + ": " + line);
      return null;
    }

    try {
      LocalDateTime timestamp = LocalDateTime.parse(parts[0]);
      int carCount = Integer.parseInt(parts[1]);
      return new TrafficRecord(timestamp, carCount);
    } catch (DateTimeParseException | NumberFormatException e) {
      LOGGER.warning("Failed to parse line: " + line + ". Error: " + e.getMessage());
    }

    return null;
  }
}