FROM openjdk:8 as builder
WORKDIR /build
ARG JAR_FILE=target/tumo-boot.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract && rm app.jar

FROM openjdk:8
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"
WORKDIR tumo-boot

COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/application/ ./

EXPOSE 8090
