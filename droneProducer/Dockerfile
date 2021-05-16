FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
RUN apt-get update
RUN apt-get -y install curl && apt-get clean
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENV SPRING_RABBITMQ_HOST=root
ENV SPRING_RABBITMQ_PORT=root
ENV DRONE_URL_UPDATE=http://loadbalance:8090/drones/%d
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
