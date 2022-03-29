create SEQUENCE public.cust_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table customer(
    id integer default nextval('cust_id_seq'),
    name varchar(150),
    pesel varchar(11),
    address varchar(150),
    birth_date date,
    phone_num bigint
);