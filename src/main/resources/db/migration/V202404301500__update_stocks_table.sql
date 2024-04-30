ALTER TABLE stocks RENAME COLUMN description TO name;

ALTER TABLE stocks
    ADD current_price NUMERIC(19, 2)            NOT NULL,
    ADD stock_type VARCHAR(10)                  NOT NULL,
    ADD bazin_price NUMERIC,
    ADD created_at TIMESTAMP WITHOUT TIME ZONE  NOT NULL,
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE  NOT NULL;
