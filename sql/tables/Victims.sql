--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.victim_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table Victims(
VICTIM_ID integer default nextval('victim_id_seq'),
NAME varchar(150),
PESEL varchar(11),
ADDRESS varchar(150),
BIRTH_DATE date,
PHONE_NUM bigint
);

