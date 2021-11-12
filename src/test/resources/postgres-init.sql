CREATE TABLE company(
    id SERIAL,
    symbol VARCHAR(6) NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT PK_companies PRIMARY KEY (id)
);