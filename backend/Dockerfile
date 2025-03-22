FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .

# Copy source code
COPY src ./src

# Package the application
RUN apt-get update && apt-get install -y maven
RUN mvn package -DskipTests

# Run the application
CMD ["java", "-jar", "target/chat-app-0.0.1-SNAPSHOT.jar"] 