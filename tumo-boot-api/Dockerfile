FROM openjdk:8u265
MAINTAINER "tycoding@sina.com"
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

WORKDIR tumo-boot
ARG JAR_FILE=target/tumo-boot.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract && rm app.jar

