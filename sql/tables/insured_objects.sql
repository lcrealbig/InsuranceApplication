--create sequence first, to use it as a value for id.
create SEQUENCE public.object_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table insured_objects(
OBJECT_ID int  default nextval('object_id_seq') ,
POLICY_LINE_NO int,
TRANSACTION_ID int,
TYPE varchar(40),
C01 varchar(40),
C02 varchar(40),
C03 varchar(40),
C04 varchar(40),
N01 int,
N02 int,
N03 int,
N04 int,
D01 date,
D02 date,
D03 date
);



