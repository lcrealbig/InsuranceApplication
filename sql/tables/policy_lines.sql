create SEQUENCE public.line_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy_lines(
POLICY_LINE_ID int default nextval('line_id_seq'),
TRANSACTION_ID int,
POLICY_ID int,
PRODUCT_LINE_TYPE varchar(30),
VERSION varchar(5)
);
/
