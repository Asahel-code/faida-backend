# Faida-Admin

# Dashboard Administrator

Dashboard Administrator is a Spring Boot application that provides an administration interface for managing a dashboard.

## Features

- Authentication: Users are required to authenticate before accessing the dashboard.
- Simple Dashboard: Provides a basic dashboard with welcome message.
- Security: Uses Spring Security for handling authentication and authorization.

## Technologies Used

- Java
- Spring Boot
- Spring Security

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven or Gradle

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
docker build -t faida-backend .
```
```bash
docker run -d --name mongodb -p 27017:27017 mongo
```
```bash
docker run -d --name faida-backend-container --link mongodb:mongodb -p 8080:8080 faida-backend
```


### Configuration

You can customize the application's behavior by modifying the configuration files located in the `src/main/resources` directory.

- `application.properties`: Contains general application configuration properties.
- `application.yml`: Alternative configuration file written in YAML format.

Also added some environment vairable in this directory in the `.env file`



