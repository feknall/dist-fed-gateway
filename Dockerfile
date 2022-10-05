FROM openjdk:18-jdk
ARG JAR_FILE=build/libs/dist-fed-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]
