FROM eclipse-temurin:21

RUN mkdir /opt/app

WORKDIR /opt/app

COPY  ./target/js-user-service-0.0.1-SNAPSHOT.jar  /opt/app

RUN mkdir /opt/app/story

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/js-user-service-0.0.1-SNAPSHOT.jar"]

