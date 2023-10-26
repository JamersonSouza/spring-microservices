FROM maven:3.8.5-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests


FROM openjdk:17-ea-3-jdk-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar  ./serviceclient.jar

ARG HOST_RABBITMQ=rabbitmq-host
ARG EUREKA_SERVER=eurekaserverun

ENTRYPOINT java -jar serviceclient.jar