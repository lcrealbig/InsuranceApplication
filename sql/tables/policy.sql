create SEQUENCE public.policy_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy(
    id integer default nextval('policy_id_seq'),
    transaction_id integer,
    owner_id integer,
    type varchar(50),
    status varchar(40),
    start_date varchar(25),
    end_date varchar(25),
    product_type varchar(40),
    alt_no varchar(40),
    version varchar(5)
);
