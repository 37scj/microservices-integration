FROM adoptopenjdk:11-jdk-hotspot as compiler
ARG JAR_FILE=target/*.jar
COPY . /src
WORKDIR /src
RUN chmod +x ./mvnw
RUN ./mvnw clean 
RUN ./mvnw package

FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/*.jar
COPY --from=compiler /src/${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
# COPY --from=builder dependencies/ ./
# COPY --from=builder snapshot-dependencies/ ./
ENV SPRING_RABBITMQ_HOST=root
ENV SPRING_RABBITMQ_PORT=root
ENV DRONE_URL_UPDATE=http://loadbalance:8090/drones/%d
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
