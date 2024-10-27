FROM openjdk:24-ea-21

ADD ./build/libs/spanish-weather-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]