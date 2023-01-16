FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/quotes

COPY ${JAR_FILE} /opt/quotes/qapi_app.jar

ENTRYPOINT ["java","-jar","/opt/quotes/qapi_app.jar"]