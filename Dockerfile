FROM amd64/ubuntu
RUN apt-get update
RUN apt-get install -y default-jre
RUN mkdir app
COPY target/spring-boot-samples-1.0.jar app/spring-boot-samples.jar
WORKDIR app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-samples.jar"]