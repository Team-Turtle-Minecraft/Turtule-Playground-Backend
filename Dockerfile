FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=./build/libs/minecraft_service-0.0.1-SNAPSHOT.jar
ARG PROFILES
ARG ENV

COPY ${JAR_FILE} turtle-playground-spring-boot-app.jar

RUN mkdir -p /app/mm
RUN mkdir -p /app/mm/images

ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul",  "-Dspring.profiles.active=${PROFILES}", "-Dserver.env=${ENV}", "-jar", "turtle-playground-spring-boot-app.jar"]
