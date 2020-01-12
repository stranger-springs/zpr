USE zpr;

DELETE
FROM index_entry;
DELETE
FROM index_entry_type;
DELETE
FROM index_type;

DELETE
FROM aggregation_entry;
DELETE
FROM entry_type;
DELETE
FROM aggregation;
DELETE
FROM aggregation_type;

DELETE
FROM currency;
DELETE
FROM currency_type;

INSERT INTO currency_type(name)
VALUES ('bitcoin'),
       ('zcash'),
       ('ethernum'),
       ('litecoin');

INSERT INTO index_type(name)
VALUES ('sma'),
       ('ema'),
       ('rsi');

INSERT INTO index_entry_type(name, window_size, index_type_id)
VALUES ('sma5', 5, (SELECT id FROM index_type WHERE index_type.name = 'sma' LIMIT 1)),
       ('sma9', 5, (SELECT id FROM index_type WHERE index_type.name = 'sma' LIMIT 1)),
       ('sma14', 5, (SELECT id FROM index_type WHERE index_type.name = 'sma' LIMIT 1)),
       ('ema5', 5, (SELECT id FROM index_type WHERE index_type.name = 'ema' LIMIT 1)),
       ('ema9', 5, (SELECT id FROM index_type WHERE index_type.name = 'ema' LIMIT 1)),
       ('ema14', 5, (SELECT id FROM index_type WHERE index_type.name = 'ema' LIMIT 1)),
       ('rsi14', 5, (SELECT id FROM index_type WHERE index_type.name = 'rsi' LIMIT 1));

INSERT INTO aggregation_type(name)
VALUES ('minute'),
       ('hourly'),
       ('daily'),
       ('monthly');

INSERT INTO entry_type(name)
VALUES ('min'),
       ('max'),
       ('avg');