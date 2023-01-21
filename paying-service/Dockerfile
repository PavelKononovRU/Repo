FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/ps

COPY ${JAR_FILE} /opt/ps/paying_service_app.jar

ENTRYPOINT ["java","-jar","/opt/ps/paying_service_app.jar"]