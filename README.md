# Traffic Car Analyzer Challenge

## Overview
This project implements a traffic data analysis tool for an automated traffic counter.
The counter records the number of cars passing every half hour.  
The program reads this data from a file and produces summary analytics.

The solution is written in **Java 21**, uses **JUnit 5** for testing, and follows
clean, production-ready coding practices.

---

## Problem Statement
Given an input file where each line contains:
- An ISO-8601 timestamp (`yyyy-MM-ddTHH:mm:ss`) representing the start of a half-hour window
- The number of cars counted in that half hour

The program outputs:
1. Total number of cars seen
2. Daily totals (cars per calendar day)
3. Top 3 half-hour periods with the most cars
4. The **least busy 1.5-hour period** (3 contiguous half-hour records with the lowest total)

---

## Example Input
# Traffic Car Analyzer Challenge

## Overview
This project implements a traffic data analysis tool for an automated traffic counter.
The counter records the number of cars passing every half hour.  
The program reads this data from a file and produces summary analytics.

The solution is written in **Java 21**, uses **JUnit 5** for testing, and follows
clean, production-ready coding practices.

---

## Problem Statement
Given an input file where each line contains:
- An ISO-8601 timestamp (`yyyy-MM-ddTHH:mm:ss`) representing the start of a half-hour window
- The number of cars counted in that half hour

The program outputs:
1. Total number of cars seen
2. Daily totals (cars per calendar day)
3. Top 3 half-hour periods with the most cars
4. The **least busy 1.5-hour period** (3 contiguous half-hour records with the lowest total)

---

## Example Input
# Traffic Car Analyzer Challenge

## Overview
This project implements a traffic data analysis tool for an automated traffic counter.
The counter records the number of cars passing every half hour.  
The program reads this data from a file and produces summary analytics.

The solution is written in **Java 21**, uses **JUnit 5** for testing, and follows
clean, production-ready coding practices.

---

## Problem Statement
Given an input file where each line contains:
- An ISO-8601 timestamp (`yyyy-MM-ddTHH:mm:ss`) representing the start of a half-hour window
- The number of cars counted in that half hour

The program outputs:
1. Total number of cars seen
2. Daily totals (cars per calendar day)
3. Top 3 half-hour periods with the most cars
4. The **least busy 1.5-hour period** (3 contiguous half-hour records with the lowest total)

---

## Example Input
2021-12-01T05:00:00 5
2021-12-01T05:30:00 12
2021-12-01T06:00:00 14


---

## Design Decisions
- **Java 21 records** are used for immutable domain objects
- A **sliding window algorithm** is used to compute the least busy period efficiently
- Records are sorted chronologically before analysis
- Tests validate business behavior rather than implementation details

---

## How to Build and Run

### Build & Test
```bash
mvn clean test

Run the Application

java -jar target/traffic-car-analyzer.jar <input-file>

Project Structure 
src
 ├── main
 │   └── java
 │       └── com.aips.traffic
 │           ├── domain
 │           ├── service
 │           ├── output
 │           └── TrafficCarAnalyzerChallenge.java
 └── test
     └── java
         └── com.aips.traffic

Technologies Used

Java 21

Maven

JUnit 5

AssertJ