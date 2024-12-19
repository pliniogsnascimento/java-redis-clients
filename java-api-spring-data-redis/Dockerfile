FROM maven:3.9.9-amazoncorretto-23-alpine AS build
RUN mkdir /app
WORKDIR /app

ADD . /app/
RUN mvn package -Dmaven.test.skip && ls /app/target
ENTRYPOINT [ "java", "-jar", "/app/target/java-redis-demo-0.0.1-SNAPSHOT.jar" ]