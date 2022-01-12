--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.claim_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;


create table Claim (
claim_id integer default nextval('claim_id_seq'),
policy_line_id varchar(30),
policy_id INTEGER,
status varchar(30),
claim_type varchar(30),
claim_description varchar,
claim_date date,
last_status_update date,
occurency_place varchar,
version varchar
);
