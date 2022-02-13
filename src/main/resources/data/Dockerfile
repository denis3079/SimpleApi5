FROM postgres
COPY ./data.sql /docker-entrypoint-initdb.d/
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root