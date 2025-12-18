# Traffic Car Analyzer Challenge -AIPS Coding Challeng
📋 Overview
A professional Java 21 solution for analyzing traffic counter data from automated road sensors. The counter records vehicles every half-hour, and this application provides comprehensive analytics on traffic patterns.

Key Features:

✅ Production-ready: Clean architecture, comprehensive testing, error handling

✅ Modern Java 21: Uses Records, Streams, and modern APIs

✅ One-command setup: Automatic installation and configuration

✅ Comprehensive testing: 100+ test cases with edge coverage

✅ User-friendly: Clear output, helpful error messages, multiple sample datasets

🎯 Problem Statement
Input Format
Each line in the input file contains:

Timestamp: ISO 8601 format (yyyy-MM-ddTHH:mm:ss)

Car count: Non-negative integer

Example:
2021-12-01T05:00:00 5
2021-12-01T05:30:00 12
2021-12-01T06:00:00 14


Required Outputs
The program must output:

Total cars - Sum of all cars in the input

Daily totals - Cars grouped by date (e.g., 2021-12-01 179)

Top 3 half-hour periods - Periods with highest car counts

1.5-hour period with least cars - Three contiguous half-hours with lowest total

🚀 Quick Start
Prerequisites
Java 21 or higher (will be installed automatically if missing)

Maven 3.6+ (will be installed automatically if missing)

One-Command Run
# Make scripts executable (first time only)
chmod +x  run.sh 

# Run with default AIPS sample data (ONly give correct path  sample file in script
./run.sh

Manual Build & Run
# Build and test
mvn clean compile test

# Create executable JAR
mvn package

# Run with sample data
java -jar target/traffic-car-analyzer-1.0-SNAPSHOT-jar-with-dependencies.jar /Users/vikasmalviya/Downloads/TrafficCarAnalyzerChallenge/src/main/resources/traffic_data.txt

📊 Sample Output

========================================
TRAFFIC DATA ANALYSIS REPORT
========================================

Total cars: 337

Daily totals:
2021-12-01 179
2021-12-05 81
2021-12-08 134
2021-12-09 4

Top 3 half hours:
2021-12-01T07:30:00 46
2021-12-01T08:00:00 42
2021-12-08T18:00:00 33

1.5 hour period with least cars:
2021-12-01T15:00:00 9
2021-12-01T15:30:00 11
2021-12-01T23:30:00 0
(Total: 20 cars)

========================================


Architecture Design

Clean Architecture Layers

┌─────────────────────────────────────────┐
│            TrafficAnalyzer              │ ← Main entry point (orchestration)
├─────────────────────────────────────────┤
│          ReportFormatter                │ ← Presentation layer
├─────────────────────────────────────────┤
│    TrafficAnalyzerService               │ ← Business logic
│        TrafficDataParser                │ ← Input processing
├─────────────────────────────────────────┤
│    TrafficRecord   DailyTotal           │ ← Domain models (immutable)
└─────────────────────────────────────────┘

Key Design Decisions
Immutability: All domain objects are immutable Java Records

Single Responsibility: Each class has one clear purpose

Defensive Programming: Validate all inputs at boundaries

Dependency Injection: Services are injectable for testability

Fail Fast: Validate inputs early with clear error messages

Algorithm Choices
Sliding Window: O(n) algorithm for finding least busy 1.5-hour period

Stream API: Functional-style processing for readability and performance

Grouping Collector: Efficient daily aggregation using Collectors.groupingBy

📁 Project Structure
traffic-car-analyzer/
├── src/
│   ├── main/
│   │   ├── java/com/aips/traffic/
│   │   │   ├── domain/           # Immutable data models (TrafficRecord, DailyTotal)
│   │   │   ├── service/          # Business logic (TrafficDataParser, TrafficAnalyzerService)
│   │   │   ├── output/           # Presentation layer (ReportFormatter)
│   │   │   └── TrafficCarAnalyzerChallenge.java  # Main entry point
│   │   └── resources/
│   │       └── traffic_data.txt  # Default input file
│   └── test/
│       ├── java/com/aips/traffic/
│       │   ├── domain/           # Unit tests for models
│       │   ├── service/          # Unit tests for services
│       │   └── integration/      # End-to-end tests
│       └── resources/            # Test resources (if any)
├── target/                       # Maven build output (generated)
├── pom.xml                       # Maven configuration
├── run.sh                        # Run application script
└── README.md                     # Project documentation

✅ Testing Strategy
Test Pyramid
Unit Tests: 60+ tests for domain objects and business logic

Integration Tests: Full workflow with actual AIPS data

Edge Cases: Empty files, invalid data, single records

Performance: Large datasets (1000+ records)

Test Coverage Includes:
✅ Business Logic: All calculations (total, daily, top 3, least busy)

✅ Input Parsing: Valid and invalid data scenarios

✅ Edge Cases: Zero counts, negative numbers, malformed timestamps

✅ Performance: O(n) algorithms verified with large inputs

✅ Integration: End-to-end with actual challenge data