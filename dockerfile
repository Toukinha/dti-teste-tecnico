FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -q -Dmaven.test.skip=true clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar

VOLUME ["/app/data"]
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/data/books.db

ENTRYPOINT ["java","-jar","/app/app.jar"]
