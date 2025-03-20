FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-Dserver.address=0.0.0.0","-Dserver.port=8080","-jar","/app.jar"] 