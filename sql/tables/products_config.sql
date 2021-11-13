--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.pc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table products_config (
ID integer default nextval('pc_id_seq'),
PRODUCT_ID varchar(5),
START_DATE date,
END_DATE date,
VERSION varchar(5)
);