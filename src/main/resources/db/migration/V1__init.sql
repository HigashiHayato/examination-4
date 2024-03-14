CREATE TABLE addresses (
    id integer PRIMARY KEY,
    zip_code varchar(7),
    prefecture varchar(20),
    city varchar(20),
    street_address varchar(100)
);

CREATE SEQUENCE IF NOT EXISTS ADDRESS_ID_SEQ
    INCREMENT BY 1
    MAXVALUE 9999999999
    MINVALUE 1
    START WITH 1
;

INSERT INTO addresses (id, zip_code, prefecture, city, street_address) VALUES (nextval('ADDRESS_ID_SEQ'), '1000000', '東京都', '千代田区', '以下に掲載がない場合');
INSERT INTO addresses (id, zip_code, prefecture, city, street_address) VALUES (nextval('ADDRESS_ID_SEQ'), '1020072', '東京都', '千代田区', '飯田橋');
INSERT INTO addresses (id, zip_code, prefecture, city, street_address) VALUES (nextval('ADDRESS_ID_SEQ'), '1500043', '東京都', '渋谷区', '道玄坂');
