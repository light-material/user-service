FROM maven:3.6-jdk-11-slim

WORKDIR /usr/src/lm/
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ src/
RUN mvn clean package -Dmaven.test.skip

EXPOSE 8080
CMD ["java","-jar","user-service.jar"]
