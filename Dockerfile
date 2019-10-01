FROM maven:3.6-jdk-11 as builder

WORKDIR /usr/src/lm/
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ src/
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:11.0.4-jre-slim
WORKDIR /usr/src/lm/
COPY --from=builder /usr/src/lm/target/lm-user-service.jar .
EXPOSE 8080

CMD ["java","-jar","lm-user-service.jar"]
