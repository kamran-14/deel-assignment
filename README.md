# Deel AI Engineer Assignment

# Overview
This project is a Spring Boot application that processes transaction data and
matches users to transactions using text-based matching.

The application reads data from CSV files and exposes REST APIs to:
1. Match users with a given transaction
2. Find transactions with similar descriptions

# Tech Stack
- Java 21
- Spring Boot
- Maven

# Data Source
- users.csv: contains user id and user name
- transactions.csv: contains transaction id, amount, and description

## Task 1: Match Users to Transactions
Endpoint:
GET /match-users/{transactionId}

This API returns users whose names appear in the transaction description.

## Task 2: Similar Transaction Descriptions
Endpoint:
GET /similar-transactions?text=value

This API returns transactions with similar descriptions based on text matching.

## Task 3: Production Improvements
- Use database instead of CSV
- Use semantic embeddings
- Improve performance and scalability

## How to Run
1. Open project in Eclipse
2. Run AssignmentApplication.java
3. Open browser at http://localhost:8080
