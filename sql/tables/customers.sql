--create sequence first, to use it as a value for id.

create SEQUENCE public.cust_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table customers(
CUSTOMER_ID int default nextval('cust_id_seq'),
NAME varchar(80),
PESEL integer,
ADDRESS varchar(60),
BIRTH_DATE date,
PHONE_NUM integer
);
/

