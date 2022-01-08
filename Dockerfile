FROM maven:3.8.4-eclipse-temurin-11-alpine
COPY /target/SimpleApi1-0.0.1-SNAPSHOT.jar MyApi.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/MyApi.jar"]