--InsuranceApp2021 by Marzag, Słowik, Czarny
create SEQUENCE public.io_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table insured_objects(
OBJECT_ID integer  default nextval('io_id_seq') ,
POLICY_LINE_ID integer,
TRANSACTION_ID integer,
TYPE varchar(40),
C01 varchar(40),
C02 varchar(40),
C03 varchar(40),
C04 varchar(40),
N01 integer,
N02 integer,
N03 integer,
N04 integer,
N05 integer,
N06 integer,
N07 integer,
D01 date,
D02 date,
D03 date,
VERSION varchar(5)
);