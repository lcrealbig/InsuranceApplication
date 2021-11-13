--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.policy_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policies(
POLICY_ID integer default nextval('policy_id_seq'),
TRANSACTION_ID integer,
OWNER_ID integer,
TYPE varchar(50),
STATUS varchar(40),
START_DATE varchar(25),
END_DATE varchar(25),
PRODUCT_TYPE varchar(40),
ALT_NO varchar(40),
VERSION varchar(5)
);
