FROM maven:3.8.5-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests


FROM openjdk:17-ea-3-jdk-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar  ./eurekaserver.jar
EXPOSE 8761
ENTRYPOINT java -jar eurekaserver.jar