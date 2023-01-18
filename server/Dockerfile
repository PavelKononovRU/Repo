FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/server

COPY ${JAR_FILE} /opt/server/server_app.jar

ENTRYPOINT ["java","-jar","opt/server/server_app.jar"]