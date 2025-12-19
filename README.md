# Traffic Car Analyzer Challenge - AIPS Coding Challenge

## 📋 Overview
This repository contains a professional Java 21 solution for analyzing traffic counter data collected from automated road sensors. The solution captures vehicle data every half-hour and provides detailed analytics and insights for the given dataset.

### Key Features
- **🚀 Production-ready**: Implements clean architecture, comprehensive testing, and robust error handling.
- **⚙️ Modern Java 21**: Leverages modern Java features including Records, Streams, and advanced APIs.
- **🔧 Simple setup**: Automated installation and configuration with a one-command setup.
- **✅ Comprehensive testing**: 25+ test cases to ensure robustness and accuracy, including edge case coverage.
- **🚗 User-friendly**: Intuitive output, helpful error messages, and support for multiple sample datasets.

---

## 🎯 Problem Statement
### Input Format
The input dataset should be formatted with one entry per line, containing the following fields:

- **Timestamp**: Given in ISO 8601 format (`yyyy-MM-ddTHH:mm:ss`).
- **Car count**: A non-negative integer indicating the number of cars recorded.

#### Example Input:
```
2021-12-01T05:00:00 5
2021-12-01T05:30:00 12
2021-12-01T06:00:00 14
2021-12-01T06:30:00 15
2021-12-01T07:00:00 25
2021-12-01T07:30:00 46
2021-12-01T08:00:00 42
2021-12-01T15:00:00 9
2021-12-01T15:30:00 11
2021-12-01T23:30:00 0
2021-12-05T09:30:00 18
2021-12-05T10:30:00 15
2021-12-05T11:30:00 7
2021-12-05T12:30:00 6
2021-12-05T13:30:00 9
2021-12-05T14:30:00 11
2021-12-05T15:30:00 15
2021-12-08T18:00:00 33
2021-12-08T19:00:00 28
2021-12-08T20:00:00 25
2021-12-08T21:00:00 21
2021-12-08T22:00:00 16
2021-12-08T23:00:00 11
2021-12-09T00:00:00 4
```

### Required Outputs
The program produces the following outputs:
- **Total cars**: Sum of all car counts in the input data.
- **Daily totals**: Aggregation of car counts grouped by date.
- **Top 3 half-hour periods**: The three periods with the highest car counts.
- **1.5-hour period with least cars**: The three contiguous half-hours with the lowest combined total.

---

## 🚀 Quick Start
### Prerequisites
- **Java 21 or higher** (automatically installed if missing).
- **Maven 3.6+** (automatically installed if missing).

### One-Command Execution
Make the script executable (only needed for the first run):
```
chmod +x  run.sh
```
Run the script with the default AIPS sample data (ensure the sample file path is correct in the script):
```
./run.sh
```

### Manual Build & Run
1. **Build and Test**:
```
mvn clean compile test
```
2. **Build Executable JAR:**
```
mvn package
```
3. **Run with Sample Data:**
```
java -jar target/traffic-car-analyzer-1.0-SNAPSHOT-jar-with-dependencies.jar /Users/vikasmalviya/Downloads/TrafficCarAnalyzerChallenge/src/main/resources/traffic_data.txt
```

---

## 📊 Sample Output
<img width="1429" height="596" alt="image" src="https://github.com/user-attachments/assets/8ca06828-40b3-4d3c-9e2f-ea6b0b2b838e" />

## Video Explaination
https://drive.google.com/file/d/1s689DdaUFNNJHZiRXtw5vyfPxHkwrgwR/view


---

## 🛠 Architecture Design
### Clean Architecture Layers

![Clean Architecture Layers](https://github.com/user-attachments/assets/82b8b2fb-ce3f-415d-a8c9-310b1b2ca8b3)

### Key Design Decisions
- **Immutability**: All domain objects are implemented as immutable Java Records.
- **Single Responsibility Principle**: Each class has one well-defined responsibility.
- **Defensive Programming**: Inputs are validated at all boundaries.
- **Dependency Injection**: Services are designed to be injectable, enhancing testability.
- **Fail Fast**: Inputs are validated early to produce clear error messages.

### Algorithm Choices
- **Sliding Window Technique**: Utilizes an O(n) algorithm to efficiently identify the least busy 1.5-hour period.
- **Stream API**: Employs functional-style programming for better readability and performance.
- **Grouping Collectors**: Efficiently aggregates daily data using `Collectors.groupingBy`.

---

## 📁 Project Structure

![Project Structure](https://github.com/user-attachments/assets/233a2816-3e76-415a-a5a6-d7df7aa8ccf5)

---

## ✅ Testing Strategy
### Test Pyramid
- **Unit Tests**: Over 60 tests for domain objects and business logic.
- **Integration Tests**: End-to-end validation with actual AIPS data.
- **Edge Case Testing**: Includes scenarios such as empty files, invalid data, and single records.


### Test Coverage Includes:
- ✅ Business Logic: Validation of calculations (`total`, `daily totals`, `top 3`, `least busy periods`).
- ✅ Input Parsing: Handles both valid and invalid data scenarios.
- ✅ Edge Cases: Tests with zero car counts, malformed timestamps, etc.
- ✅ Performance: Ensures O(n) algorithms work efficiently with large datasets.
- ✅ Integration: Full end-to-end coverage using actual challenge data.

---

## 📄 License
Vikas Malviya

---

## 🖥 Contributing
Contributions are welcome! Please fork this repo and submit a pull request explaining your changes. For significant contributions, please open an issue first to discuss your ideas.

---

## Questions?
For questions, please open an issue in this repository.
