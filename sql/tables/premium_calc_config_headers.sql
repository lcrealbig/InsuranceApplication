--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.pcch_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table premium_calc_config_headers (
id integer default nextval('pcch_id_seq'),
combo_id varchar(30),
risk_id varchar(10),
combination_name varchar(30),
header_1 varchar(30),
header_2 varchar(30),
header_3 varchar(30),
header_4 varchar(30),
header_5 varchar(30),
header_6 varchar(30),
header_7 varchar(30),
header_8 varchar(30),
header_9 varchar(30),
header_10 varchar(30),
header_11 varchar(30),
header_12 varchar(30),
version varchar(30)
);
