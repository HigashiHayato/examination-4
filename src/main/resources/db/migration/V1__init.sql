CREATE TABLE addresses (
    id integer PRIMARY KEY,
    zip_code varchar(7),
    prefecture varchar(20),
    city varchar(20),
    street_address varchar(100)
);

INSERT INTO addresses VALUES (1, '1000000', '東京都', '千代田区', '以下に掲載がない場合');
INSERT INTO addresses VALUES (2, '1020072', '東京都', '千代田区', '飯田橋');
INSERT INTO addresses VALUES (3, '1500043', '東京都', '渋谷区', '道玄坂');
