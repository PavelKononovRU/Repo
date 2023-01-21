FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/sub

COPY ${JAR_FILE} /opt/sub/subscription_app.jar

ENTRYPOINT ["java","-jar","/opt/sub/subscription_app.jar"]