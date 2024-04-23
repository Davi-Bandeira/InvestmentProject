CREATE TABLE users (
    id                 SERIAL       NOT NULL,
    username           VARCHAR(255) NOT NULL,
    email              VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP    NOT NULL,
    update_timestamp   TIMESTAMP    NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
