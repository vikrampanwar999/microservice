FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} checkout_service.jar
EXPOSE 8301-8400
ENTRYPOINT ["java","-jar","/checkout_service.jar"]