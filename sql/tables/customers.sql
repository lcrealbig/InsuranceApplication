--create sequence first, to use it as a value for id.

create SEQUENCE public.cust_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table customers(
CUSTOMER_ID integer default nextval('cust_id_seq'),
NAME varchar(80),
PESEL varchar(11),
ADDRESS varchar(80),
BIRTH_DATE date,
PHONE_NUM bigint
);
/

