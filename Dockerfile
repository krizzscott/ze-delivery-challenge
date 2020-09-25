FROM openjdk:8-alpine

MAINTAINER Christopher Rozario

RUN apk update && apk add bash

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY target/ze-delivery-challenge-1.3.0.jar $PROJECT_HOME/ze-delivery-challenge-1.3.0.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "./ze-delivery-challenge-1.3.0.jar"]