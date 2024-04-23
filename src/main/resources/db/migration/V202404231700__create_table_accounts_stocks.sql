CREATE TABLE accounts_stocks (
    account_id   BIGINT       NOT NULL,
    stock_id     VARCHAR(6)   NOT NULL,
    quantity     INTEGER      NOT NULL,
    PRIMARY KEY (account_id, stock_id),
    CONSTRAINT account_id_fk FOREIGN KEY (account_id)
        REFERENCES accounts (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT stock_id_fk FOREIGN KEY (stock_id)
        REFERENCES stocks (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);