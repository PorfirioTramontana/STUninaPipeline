FROM openjdk:8-jdk-slim

WORKDIR /serve/app

COPY ./target/UninaTestPipeline-1.0-SNAPSHOT.jar /serve/app

ENV PORT 8080
EXPOSE $PORT
CMD ["java", "-jar", "UninaTestPipeline-1.0-SNAPSHOT.jar"]