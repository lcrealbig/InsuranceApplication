delete from product_config;
--1.0
INSERT INTO product_config (id, product_id, start_date, end_date, version) VALUES (1, 'M', '01.01.2021', '12.31.2021', '1.0');
--2.0
INSERT INTO product_config (id, product_id, start_date, end_date, version) VALUES (2, 'M', '01.01.2022', null, '2.0');
