FROM maven:3.9.6-eclipse-temurin-21 AS BUILD

# Copy your Maven project files into the container
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/tasks-0.0.1-SNAPSHOT.jar tasks.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tasks.jar"]