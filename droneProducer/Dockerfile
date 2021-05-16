FROM adoptopenjdk:11-jdk-hotspot as builder
ARG JAR_FILE=target/*.jar
COPY . /src
WORKDIR /src
RUN chmod +x ./mvnw
RUN ./mvnw clean install
RUN mv target/*.jar /application.jar
WORKDIR /
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /application
WORKDIR /application
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
ENV SPRING_RABBITMQ_HOST=root
ENV SPRING_RABBITMQ_PORT=root
ENV DRONE_URL_UPDATE=http://loadbalance:8090/drones/%d
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
