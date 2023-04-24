FROM openjdk:17
LABEL authors="snooker4real"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/movies-*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
