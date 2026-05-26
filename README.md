# BFHL Project

This project contains:
- **`backend/`**: A Spring Boot REST API for processing arrays and extracting structured information.

## API Specification

- **Method**: `POST`
- **Route**: `/bfhl`
- **Port**: Configurable via `PORT` env variable (defaults to `8080`)

### Request Body Format
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

### Response Format
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

## Running the Backend Locally

```bash
cd backend
mvn spring-boot:run
```

## Deploying to Railway / Render

1. Create a Git Repository:
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   ```
2. Create a new GitHub repository and push your code there.
3. Connect your repository to **Railway** or **Render**:
   - For **Render**: Choose **Web Service**, select your repo, set the build command to `mvn clean package -DskipTests` (or use the included `Dockerfile`), and set the start command to `java -jar backend/target/bfhl-api-0.0.1-SNAPSHOT.jar` (if deploying using JAR), or deploy using Docker option.
   - For **Railway**: Just connect the repository, and Railway's Nixpacks or Dockerfile support will automatically detect the Spring Boot project and deploy it.
