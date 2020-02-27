FROM adoptopenjdk/openjdk11:ubi
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]
EXPOSE 8080