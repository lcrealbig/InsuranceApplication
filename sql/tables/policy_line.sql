create SEQUENCE public.line_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy_line(
    id integer default nextval('line_id_seq'),
    transaction_id integer,
    policy_id integer,
    policy_line_type varchar(30)
);


