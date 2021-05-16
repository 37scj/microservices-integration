FROM adoptopenjdk:11-jre-hotspot as compiler
ARG JAR_FILE=target/*.jar
COPY . /src
WORKDIR /src
RUN ./mvnw clean
RUN ./mvnw package

FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/*.jar
COPY --from=compiler ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
RUN apt-get update
RUN apt-get -y install curl && apt-get clean
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/droneDB?serverTimezone=UTC
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
