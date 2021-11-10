--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.cust_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table customers(
CUSTOMER_ID integer default nextval('cust_id_seq'),
NAME varchar(150),
PESEL integer,
ADDRESS varchar(150),
BIRTH_DATE date,
PHONE_NUM bigint
);


