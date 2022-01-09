--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.pltc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy_line_types_config (
ID integer default nextval('pltc_id_seq'),
PRODUCT_ID varchar(5),
POLICY_LINE_TYPE varchar(5),
VERSION varchar(5)
);
