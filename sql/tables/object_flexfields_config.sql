--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.ofc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table object_flexfields_config(
id integer default nextval('ofc_id_seq'),
TYPE varchar(40),
OBJECT_KEY varchar(40),
OBJECT_VALUE varchar(40)
);