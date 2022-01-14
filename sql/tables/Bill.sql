--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.bill_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table Bills (
claim_id integer default nextval('bill_id_seq'),
bill_status varchar(30),
victim_id INTEGER,
bill_amount numeric,
amount_to_withdraw numeric,
version varchar

);