FROM mysql:5.7.34

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./tumo_boot.sql /docker-entrypoint-initdb.d
