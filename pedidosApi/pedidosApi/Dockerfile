FROM openjdk:17
VOLUME /tmp
EXPOSE 8084
ARG JAR_FILE=target/pedidosApi-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} pedidosApi.jar
ENTRYPOINT ["java", "-jar", "/pedidosApi.jar"]