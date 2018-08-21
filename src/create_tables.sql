CREATE TABLE banking_user (
    uid SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    age INTEGER,
    balance NUMERIC(10,2)
);
CREATE TABLE user_transaction_history(
    transaction_number SERIAL PRIMARY KEY NOT NULL,
    uid INTEGER NOT NULL,
    type VARCHAR NOT NULL,
    amount NUMERIC(10,2) NOT NULL
);