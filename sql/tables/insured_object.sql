create SEQUENCE public.io_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table insured_object(
    id integer default nextval('io_id_seq') ,
    policy_line_id integer,
    transaction_id integer,
    type varchar(40),
    c01 varchar(40),
    c02 varchar(40),
    c03 varchar(40),
    c04 varchar(40),
    n01 integer,
    n02 integer,
    n03 integer,
    n04 integer,
    n05 integer,
    n06 integer,
    n07 integer,
    d01 date,
    d02 date,
    d03 date
);