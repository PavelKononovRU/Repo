FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/subpay

COPY ${JAR_FILE} /opt/subpay/subpayment_app.jar

ENTRYPOINT ["java","-jar","/opt/subpay/subpayment_app.jar"]