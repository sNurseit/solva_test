FROM jdk-17 as builder

WORKDIR /usr/src/app

COPY src /usr/src/app
RUN mvn package

FROM openjdk:17-jre-slim

COPY --from=builder /usr/src/app/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "/app.jar"]
