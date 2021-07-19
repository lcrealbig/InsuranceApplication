--create sequence first to use it as a value for id.
create SEQUENCE public.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table users (
USER_ID varchar(20) default nextval('user_id_seq'),
PASSWORD varchar(20),
NAME varchar(70),
ADDRESS varchar(70),
TYPE varchar(30),
BIRTH_DATE date,
PESEL int
);
/
