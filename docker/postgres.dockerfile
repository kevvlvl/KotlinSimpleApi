FROM postgres:alpine3.14

COPY finance-bootstrap.sql /docker-entrypoint-initdb.d/

EXPOSE 5432