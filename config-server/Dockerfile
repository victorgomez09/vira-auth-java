<<<<<<< HEAD
FROM eclipse-temurin:21-jdk

COPY target/config-server-1.0.0.jar config-server-1.0.0.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/config-server-1.0.0.jar"]
=======
FROM eclipse-temurin:11

VOLUME /tmp

EXPOSE 8888

ARG JAR_FILE=target/config-server.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
>>>>>>> a6d1929 (code refactor)
