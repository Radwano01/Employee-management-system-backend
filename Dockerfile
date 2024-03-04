FROM maven:maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean pachage -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/employee-management-admin-0.0.1=SNAPSHOT.jar employee-management-admin.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "employee-management-admin.jar"]
