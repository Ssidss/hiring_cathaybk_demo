CREATE TABLE currency (
    en_code VARCHAR(10) PRIMARY KEY,
    zh VARCHAR(20),
    symbol VARCHAR(32),
    rate NUMERIC(16,8),
    description VARCHAR(255)
);

--- 以下是測試資料...
INSERT INTO currency(en_code, zh, rate, symbol, description)
VALUES('TQ_USD', '美元', 23740.3717, '&#36;', 'United Status Dollar');

--- SELECT ALL
SELECT en_code, zh, symbol, rate, description FROM currency;

--- SELECT by PK
SELECT en_code, zh, symbol, rate, description
FROM currency
WHERE en_code = 'TQ_USD';

UPDATE currency
SET zh = '新美元',
symbol = '',
rate = 23740.3718,
description = '更新一定行!'
WHERE en_code = 'TQ_USD'

DELETE FROM currency WHERE en_code = 'TQ_USD';

