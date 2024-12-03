FROM amazoncorretto:17.0.7-alpine

COPY ./build/libs/kafka-mentoring.jar ./kafka-mentoring.jar

ENV TZ=Europe/Moscow

ENTRYPOINT ["java", "-jar", "./kafka-mentoring.jar"]