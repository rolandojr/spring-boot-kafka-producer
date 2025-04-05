FROM eclipse-temurin:17-jre-alpine-3.21
LABEL authors="Rolando Ramos"
ENV PORT 9090
COPY target/spring-boot-kafka-producer-1.0.0-SNAPSHOT.jar app-1.0.0.jar
ENTRYPOINT ["java","-jar","/app-1.0.0.jar"]
