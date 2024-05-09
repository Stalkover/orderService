FROM eclipse-temurin:21

RUN adduser --system spring-boot && addgroup --system spring-boot && adduser spring-boot spring-boot
USER spring-boot

WORKDIR /app

COPY target/deliveryservice-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]