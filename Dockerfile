FROM openjdk:24-ea-21

COPY ./build/libs/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]