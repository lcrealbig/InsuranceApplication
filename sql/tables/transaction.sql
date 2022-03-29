create SEQUENCE public.transactions_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table transaction(
    id integer default nextval('transactions_seq'),
    modified_by varchar(30),
    timestamp varchar(40),
    transaction_type varchar(20)
);
