package com.aips.traffic.Integration;

import com.aips.traffic.TrafficCarAnalyzerChallenge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficAnalyzerIntegrationTest {

    @TempDir
    Path tempDir;

    @Test
    void main_ValidInputFile_ProducesCorrectOutput() throws IOException {
        // Arrange
        Path inputFile = tempDir.resolve("input.txt");
        Files.writeString(inputFile, """
            2021-12-01T05:00:00 5
            2021-12-01T05:30:00 10
            2021-12-01T06:00:00 15
            """);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // Act
            TrafficCarAnalyzerChallenge.main(new String[]{inputFile.toString()});
        } finally {
            System.setOut(System.out);
        }

        // Assert
        String output = outputStream.toString();
        assertThat(output).contains("Total cars: 30");
        assertThat(output).contains("2021-12-01 30");
    }
}