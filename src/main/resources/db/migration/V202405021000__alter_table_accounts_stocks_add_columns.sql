ALTER TABLE accounts_stocks
    ADD avg_price        NUMERIC(19, 2)    NOT NULL,
    ADD percent_wallet   NUMERIC(19, 2)    NOT NULL,
    ADD total_cost       NUMERIC(19, 2)    NOT NULL,
    ADD total_value      NUMERIC(19, 2)    NOT NULL;