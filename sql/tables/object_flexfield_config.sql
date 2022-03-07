create SEQUENCE public.ofc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table object_flexfield_config(
    id integer default nextval('ofc_id_seq'),
    type varchar(40),
    object_key varchar(40),
    object_value varchar(40)
);