--InsuranceApp2021 by Marzag, Słowik, Czarny
create SEQUENCE public.plvc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table product_line_version_config (
ID integer default nextval('plvc_id_seq'),
PRODUCT_LINE_ID varchar(5),
VERSION varchar(5),
START_DATE date,
END_DATE date
);