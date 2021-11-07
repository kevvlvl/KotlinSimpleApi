CREATE TABLE company(
    id SERIAL,
    symbol VARCHAR(6) NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT PK_companies PRIMARY KEY (id)
);

INSERT INTO company(symbol, name)
VALUES('ABC', 'A Boring Company');

INSERT INTO company(symbol, name)
VALUES('ACME', 'Top Inventors');

INSERT INTO company(symbol, name)
VALUES('LATX', 'Vandelay Industries');