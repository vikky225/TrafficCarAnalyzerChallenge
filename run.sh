#!/bin/bash

# Exit on error
set -e

# Define colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo "=========================================="
echo "Traffic Data Analyzer - AIPS Coding Challenge"
echo "=========================================="
echo

# Function to print colored messages
print_green() {
    echo -e "${GREEN}$1${NC}"
}

print_red() {
    echo -e "${RED}$1${NC}"
}

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    print_red "Maven is not installed. Please install Maven to build the project."
    exit 1
fi

# Check if Java is installed (at least version 21)
if ! command -v java &> /dev/null; then
    print_red "Java is not installed. Please install Java 21 or higher."
    exit 1
fi

# Get Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d '"' -f 2 | cut -d '.' -f 1)
if [ "$JAVA_VERSION" -lt 21 ]; then
    print_red "Java version is $JAVA_VERSION. Please install Java 21 or higher."
    exit 1
fi

# Clean and build the project
echo "Building the project..."
if ! mvn clean compile; then
    print_red "Build failed!"
    exit 1
fi
print_green "Build successful."

# Run tests
echo "Running tests..."
if ! mvn test; then
    print_red "Tests failed!"
    exit 1
fi
print_green "Tests passed."

# Package the application
echo "Packaging the application..."
if ! mvn package -DskipTests; then
    print_red "Packaging failed!"
    exit 1
fi
print_green "Packaging successful."

# Determine the input file: use the first argument or default to sample data
INPUT_FILE=${1:-"/Users/vikasmalviya/Downloads/TrafficCarAnalyzerChallenge/src/main/resources/traffic_data.txt"}

# Check if the input file exists
if [ ! -f "$INPUT_FILE" ]; then
    print_red "Input file not found: $INPUT_FILE"
    echo "Please provide a valid input file."
    echo "Usage: $0 [input-file]"
    exit 1
fi

# Run the application
echo "Running the application with input file: $INPUT_FILE"
echo "=========================================="
echo

java -jar target/traffic-car-analyzer-1.0-SNAPSHOT-jar-with-dependencies.jar "$INPUT_FILE"

echo
print_green "Done."