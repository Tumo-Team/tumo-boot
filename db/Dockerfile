FROM mysql:5.7.34

COPY utf8mb4.cnf /etc/mysql/conf.d/utf8mb4.cnf

COPY ./tumo_boot.sql /docker-entrypoint-initdb.d
