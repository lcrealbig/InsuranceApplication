--create sequence first to use it as a value for id.
create SEQUENCE public.line_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy_lines(
LINE_ID int default nextval('line_id_seq'),
POLICY_NO int,
PRODUCT_LINE_TYPE varchar(30)
);
/
