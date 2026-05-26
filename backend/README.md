# BFHL API

REST API for BFHL data processing.

## Tech Stack
- Java 17
- Spring Boot 3.2.5
- Maven

## Endpoints

### POST /bfhl
Processes an array of mixed data (numbers, alphabets, special characters).

**Request:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response:**
```json
{
  "is_success": true,
  "user_id": "aditya_yadav_23022005",
  "email": "adityayadav230093@acropolis.in",
  "roll_number": "0827IT231015",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### GET /bfhl
Returns the operation code.

## Running Locally
```bash
mvn spring-boot:run
```

## Running Tests
```bash
mvn test
```

## Deployment
Deploy using Docker or directly on Railway/Render.
