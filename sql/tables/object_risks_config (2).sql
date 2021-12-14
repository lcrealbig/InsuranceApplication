--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.orc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table object_risks_config (
id integer default nextval('orc_id_seq'),
object_type varchar(10),
object_risks varchar(30),
version varchar(30)
);

