--create sequence first to use it as a value for id.
create SEQUENCE public.policy_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policies(
POLICY_ID int default nextval('policy_id_seq'),
OWNER_ID int,
TYPE varchar(50),
STATUS varchar(40),
START_DATE varchar(25),
END_DATE varchar(25),
PRODUCT_TYPE varchar(40),
ALT_NO varchar(40)
);
/