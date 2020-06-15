FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cart_service.jar
EXPOSE 8201-8300
ENTRYPOINT ["java","-jar","/cart_service.jar"]