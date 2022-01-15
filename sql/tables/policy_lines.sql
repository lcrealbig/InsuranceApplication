--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.line_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy_lines(
POLICY_LINE_ID integer default nextval('line_id_seq'),
TRANSACTION_ID integer,
POLICY_ID integer,
POLICY_LINE_TYPE varchar(30),
VERSION varchar(5)
)


