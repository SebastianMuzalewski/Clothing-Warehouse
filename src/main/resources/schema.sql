CREATE TABLE if NOT EXISTS Clothing (
    id identity,
    name VARCHAR(50) NOT NULL,
    brandFrom VARCHAR(50) NOT NULL,
    yearCreated NUMERIC NOT NULL,
    price NUMERIC NOT NULL
);
