
#
# Build stage
#
FROM maven:3-amazoncorretto-17-debian AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM alpine:3.17
RUN apk add --no-cache &&\
        wget -O /etc/apk/keys/amazoncorretto.rsa.pub https://apk.corretto.aws/amazoncorretto.rsa.pub && \
        echo "https://apk.corretto.aws" >> /etc/apk/repositories && \
        apk update &&\
        apk add amazon-corretto-17
COPY --from=build /home/app/target/movies-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080:8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]