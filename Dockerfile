FROM openjdk:8-jre
MAINTAINER tycoding@sina.com

WORKDIR /build

ADD ./target/tumo-boot.jar ./app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=prod"]
