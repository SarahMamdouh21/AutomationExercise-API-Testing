# AutomationExercise API Testing & Automation

## Project Overview

This project is an API testing and automation framework developed to test the REST APIs provided by [AutomationExercise](https://automationexercise.com/).

The framework uses REST Assured with Java and TestNG to validate API responses, status codes, response data, and different positive and negative scenarios.

## Technologies Used

- Java 21
- REST Assured 5.5.0
- TestNG 7.10.2
- Maven
- Jackson (JSON Serialization & Deserialization)
- Allure TestNG

## Project Structure

```text
APITesting/
├── src/test/java/
│   ├── base/
│   │   └── BaseTest.java
│   ├── endpoints/
│   │   ├── BrandEndpoints.java
│   │   ├── ProductEndpoints.java
│   │   └── UserEndpoints.java
│   ├── models/
│   │   ├── BrandsResponse.java
│   │   ├── Category.java
│   │   ├── Product.java
│   │   ├── ProductsResponse.java
│   │   └── UserType.java
│   ├── tests/
│   │   ├── BrandTests.java
│   │   ├── ProductTests.java
│   │   └── UserTests.java
│   └── utils/
│       ├── ConfigReader.java
│       └── JsonReader.java
├── src/test/resources/
│   ├── config.properties
│   └── payloads/
├── testng.xml
├── pom.xml
└── .gitignore
```

## API Test Coverage

### Products APIs

- Get All Products List
- POST to All Products List (Negative Scenario)
- Search Product
- Search Product without Required Parameter

### Brands APIs

- Get All Brands List
- PUT to All Brands List (Negative Scenario)

### User APIs

- Register User
- Verify Login with Valid Credentials
- Verify Login with Invalid Credentials
- Verify Login with Missing Parameters
- Verify Login Using Unsupported HTTP Method
- Delete User Account
- Update User Account
- Get User Account Details by Email

## Framework Features

- Reusable API endpoint methods
- Centralized request specification
- REST Assured API testing
- TestNG assertions and test execution
- JSON response validation
- JSON Serialization and Deserialization using Jackson
- Positive and negative API testing
- Reusable data and configuration utilities
- Organized test structure using BaseTest, Endpoints, Models, Tests, and Utilities

## Configuration

The base URL is stored in:

```text
src/test/resources/config.properties
```

Example:

```properties
baseUrl=https://automationexercise.com
```

## How to Run the Tests

### Prerequisites

- Java JDK 21
- Maven
- IntelliJ IDEA (recommended)

### Clone the Repository

```bash
git clone https://github.com/SarahMamdouh21/AutomationExercise-API-Testing.git
```

### Navigate to the Project Directory

```bash
cd AutomationExercise-API-Testing
```

### Run the Test Suite

```bash
mvn test
```

Or run the test suite using the TestNG configuration:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Execution

The test suite is configured using `testng.xml` and includes:

- Product API Tests
- Brand API Tests
- User API Tests

## Postman Collection
Import `postman/AutomationExercise_API.postman_collection.json` into Postman and run the whole collection in order.

## Reporting

Allure TestNG is included as a project dependency for test reporting integration.

---

**Author:** Sarah Mamdouh

**Project Type:** API Testing & Automation
