FROM eclipse-temurin:21-jdk

COPY target/config-server-1.0.0.jar config-server-1.0.0.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/config-server-1.0.0.jar"]
