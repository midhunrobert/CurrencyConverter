# Currency Converter API

## Overview
This is a Spring Boot-based Currency Converter API that fetches real-time exchange rates from an external API and provides currency conversion functionalities.

## Features
- Fetch real-time exchange rates for a given base currency.
- Convert an amount from one currency to another.
- Handles errors for invalid currency codes and API failures.

## Technologies Used
- Java 17
- Spring Boot 3
- Maven
- JUnit (for testing)
- RestTemplate (for external API calls)

---

## How to Run the Application Locally

### 1. Prerequisites
Ensure you have the following installed:
- Java 17 or later
- Maven
- Git (optional, for cloning the repository)


### 2. Build the Project
Navigate to the project root directory and run:
mvn clean install

### 3. Run the Application
Start the application using:
mvn spring-boot:run
By default, the application will run on `http://localhost:8080`.


## API Documentation

### 1. Get Exchange Rates
#### Endpoint:
GET /api/rates?base={currency_code}
#### Example Request:
GET http://localhost:8080/api/rates?base=USD
#### Example Response:
{
    "EUR": 0.85,
    "GBP": 0.75,
    "INR": 74.5
}

### 2. Convert Currency
#### Endpoint:
POST /api/convert
#### Request Body:
{
    "from": "USD",
    "to": "EUR",
    "amount": 100
}

#### Example Response:
{
    "from": "USD",
    "to": "EUR",
    "amount": 100,
    "convertedAmount": 85
}

### Error Handling
- If an invalid currency code is provided:
{
    "error": "Invalid currency code"
}
- If the external API is unavailable:
{
    "error": "External API is unavailable"
}


## Testing
To run the unit tests, execute:
mvn test


