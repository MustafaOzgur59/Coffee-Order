# ☕ Coffee Order App

A simple console based Java application for managing a coffee menu, placing orders, and formatting output — built using Maven & JUnit 5.

## Features

- **Menu Service** — Stores and displays available coffee types with prices.
- **Order Service** — Handles order creation, cost calculation, and item listing.
- **Formatting Service** — Produces human-friendly console output.
- **Console UI** — CLI for placing orders and exiting.
- **Extensive Tests** — Unit tests for each layer + integration tests for the full app flow.

## Running the App

### 1. Clone the repository

```bash
git clone https://github.com/MustafaOzgur59/Coffee-Order.git
cd Coffee-Order
```

### 2. Compile and run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="Main"
```

## Running Tests

Run **all tests** (unit + integration):

```bash
mvn test
```
