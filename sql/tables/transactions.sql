CREATE SEQUENCE public.transactions_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table transactions(
TRANSACTION_ID int default nextval('transactions_seq'),
MODIFIED_BY varchar(30),
TIMESTAMP varchar(40),
TRANSACTION_TYPE varchar(20)
);
/