ALTER TABLE accounts
    ADD total_cost       NUMERIC(19, 2)    DEFAULT 0 NOT NULL,
    ADD total_value      NUMERIC(19, 2)    DEFAULT 0 NOT NULL;