FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} discovery_service.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/discovery_service.jar"]