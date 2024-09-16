CREATE TABLE if NOT EXISTS history_transaction_table
(
    id                 SERIAL PRIMARY KEY,
    account_from       INTEGER     NOT NULL,
    account_to         INTEGER     NOT NULL,
    currency_shortname VARCHAR(3)  NOT NULL,
    amount double precision	 NOT NULL,
    expense_category   VARCHAR(50) NOT NULL
);