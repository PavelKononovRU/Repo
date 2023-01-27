FROM openjdk:17-oracle

ARG JAR_FILE=target/*.jar

RUN mkdir /opt/stubpay

COPY ${JAR_FILE} /opt/stubpay/stubpayment_app.jar

ENTRYPOINT ["java","-jar","/opt/stubpay/stubpayment_app.jar"]