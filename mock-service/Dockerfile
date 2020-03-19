FROM gradle:5.2.1-jdk11-slim AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build --no-daemon

FROM adoptopenjdk/openjdk11:ubi
COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]
EXPOSE 8080