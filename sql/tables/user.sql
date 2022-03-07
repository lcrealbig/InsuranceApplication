create SEQUENCE public.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table users (
    user_id varchar(20) default nextval('user_id_seq'),
    password varchar(20),
    name varchar(70),
    address varchar(70),
    type varchar(30),
    birth_date date,
    pesel varchar(11)
);