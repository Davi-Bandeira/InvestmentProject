CREATE TABLE accounts (
    id              SERIAL  NOT NULL,
    description     VARCHAR(255),
    user_id         BIGINT,
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT users_fk FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
