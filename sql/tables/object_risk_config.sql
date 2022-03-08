create SEQUENCE public.orc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table object_risk_config (
    id integer default nextval('orc_id_seq'),
    object_type varchar(10),
    risk_id varchar(30),
    required varchar(20),
    version varchar(30)
);

