# Faida-Backend

Faida-Backend is a Spring Boot application that provide api end point for intergration to faida app and faida web dashboard.

## Features

- Account creating api
- Authentication api
- Fetch all uses api

## Technologies Used

- Java
- Spring Boot

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven or Gradle
- Docker
- Mongo DB

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Asahel-code/faida-backend.git
```

2. Navigate to the project directory:

```bash
cd faida-backend
``` 

3. Build the project using Maven or Gradle:

#### Maven
```bash
mvn compile
```

#### Gradle
```bash
./gradlew build
```

4. Run the application using Maven, Gradle or docker:

#### Maven 
```bash
mvn package
```

#### Gradle
```bash
./gradlew bootRun
```

### Docker
```bash
docker-compose up --build
```


### Configuration

You can customize the application's behavior by modifying the configuration files located in the `src/main/resources` directory.

- `application.properties`: Contains general application configuration properties.
- `application.yml`: Alternative configuration file written in YAML format.



