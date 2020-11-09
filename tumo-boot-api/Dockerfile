FROM anapsix/alpine-java:8_server-jre_unlimited
MAINTAINER tycoding@sina.com
RUN mkdir -p /tumo-boot/tumo-boot-api
WORKDIR /tumo-boot/tumo-boot-api
EXPOSE 8080
ADD ./target/tumo-boot.jar ./app.jar
CMD java -Djava.security.egd=file:/dev/./urandom -jar ./app.jar --spring.profiles. active=test
