CREATE TABLE currency (
    en_code VARCHAR(10) PRIMARY KEY,
    zh VARCHAR(20),
    symbol VARCHAR(32),
    rate NUMERIC(16,8),
    description VARCHAR(255)
);
