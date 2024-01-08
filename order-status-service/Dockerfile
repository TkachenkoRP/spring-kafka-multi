FROM openjdk:21-oracle
WORKDIR /app
ARG JAR_FILE
COPY build/libs/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]