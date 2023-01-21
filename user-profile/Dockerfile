FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/up

COPY ${JAR_FILE} /opt/up/user_profile_app.jar

ENTRYPOINT ["java","-jar","/opt/up/user_profile_app.jar"]