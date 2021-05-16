FROM adoptopenjdk:11-jdk-hotspot as builder
ARG JAR_FILE=target/*.jar
COPY . /src
WORKDIR /src
RUN chmod +x ./mvnw
RUN ./mvnw clean package install
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
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/droneDB?serverTimezone=UTC
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
