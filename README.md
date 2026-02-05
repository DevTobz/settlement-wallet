# Wallet Demo API

A Spring Boot application that provides wallet creation, wallet funding/debiting, and wallet-to-wallet transfers with idempotent transaction handling.

## Features

- Create wallets for customers
- Retrieve wallet details
- Create debit and credit transactions
- Wallet-to-wallet transfers
- Idempotency support to prevent duplicate transactions
- Centralized API response structure
- PostgreSQL database
- CORS enabled

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Project Structure

```
src/main/java/com/_jasettlement/demo
│
├── controllers        # REST controllers
├── entity
│   ├── dto            # Data Transfer Objects
│   ├── request        # API request models
│   ├── response       # API response wrapper
│   └── exceptions
├── model              # JPA entities
├── repository         # JPA repositories
├── service
│   ├── serviceImpl    # Business logic implementations
├── util               # Utility classes
└── Enum               # Enums (TransactionType, Status, etc.)
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL

### Database Configuration

Update `application.properties` as needed:

```properties
spring.application.name=wallet-demo

spring.datasource.url=jdbc:postgresql://localhost:5432/walletdb
spring.datasource.username=postgres
spring.datasource.password=password

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### ⚠️ Data Seeder (Important)

This project includes a data seeder for initial data (customers, etc.).

**Required Steps:**

1. Uncomment the data seeder class
2. Run the application once to seed the database
3. Comment the data seeder back out
4. Restart the application

⚠️ **Warning:** Leaving the seeder enabled will cause duplicate records.

## API Documentation

### Base URL

```
http://localhost:8080
```

### Wallet Endpoints

#### Create Wallet

**POST** `/wallets/`

**Request Body:**

```json
{
  "currencyCode": "NGN",
  "customerEmail": "user@example.com"
}
```

**cURL Example:**

```bash
curl -X POST http://localhost:8080/wallets/ \
-H "Content-Type: application/json" \
-d '{
  "currencyCode": "NGN",
  "customerEmail": "user@example.com"
}'
```

#### Get Wallet By ID

**GET** `/wallets/{id}`

**cURL Example:**

```bash
curl -X GET http://localhost:8080/wallets/1
```

### Transaction Endpoints

#### Create Transaction (Debit / Credit)

**POST** `/transactions/`

**Request Body:**

```json
{
  "walletPublicId": "WALLET_PUBLIC_ID",
  "key": "unique-idempotency-key",
  "amount": 5000,
  "transactionType": "CREDIT"
}
```

**Parameters:**
- `transactionType`: Can be `CREDIT` or `DEBIT`

**cURL Example:**

```bash
curl -X POST http://localhost:8080/transactions/ \
-H "Content-Type: application/json" \
-d '{
  "walletPublicId": "WALLET_PUBLIC_ID",
  "key": "txn-12345",
  "amount": 5000,
  "transactionType": "CREDIT"
}'
```

#### Wallet Transfer

**POST** `/transactions/transfer`

**Request Body:**

```json
{
  "sendingWalletPublicId": "SENDER_WALLET_ID",
  "receivingWalletPublicId": "RECEIVER_WALLET_ID",
  "key": "transfer-idempotency-key",
  "amount": 2000
}
```

**cURL Example:**

```bash
curl -X POST http://localhost:8080/transactions/transfer \
-H "Content-Type: application/json" \
-d '{
  "sendingWalletPublicId": "WALLET_ABC123",
  "receivingWalletPublicId": "WALLET_XYZ456",
  "key": "transfer-001",
  "amount": 2000
}'
```

## API Response Format

All endpoints return a standard response structure:

```json
{
  "timeStamp": "2026-02-05T12:00:00Z",
  "isSuccessful": true,
  "status": 201,
  "message": "Operation successful",
  "data": {}
}
```

## Idempotency Behavior

- Each transaction uses an idempotency key
- Duplicate requests with the same key are rejected
- Wallet transfers internally create:
  - One DEBIT transaction (from sender)
  - One CREDIT transaction (to receiver)

## CORS Configuration

CORS is enabled on the wallet controller:

```java
@CrossOrigin(origins = "*")
```

This allows requests from any frontend during development.

⚠️ **For production, restrict allowed origins.**

## Running the Application

```bash
mvn spring-boot:run
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/wallet-demo-0.0.1-SNAPSHOT.jar
```

## License

This project is a demonstration application.

## Contributing

This is a demo project. Feel free to fork and modify as needed.
