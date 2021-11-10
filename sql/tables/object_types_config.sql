--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.otc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table object_types_config (
ID integer default nextval('otc_id_seq'),
POLICY_LINE_ID varchar(5),
OBJ_TYPE varchar(5),
VERSION varchar(5)
);
--CONFIG
insert into object_types_config
(policy_line_id, obj_type , version)
values ('MOT','VEH','1.0');
insert into object_types_config
(policy_line_id, obj_type , version)
values ('MOT','DRI','1.0');
insert into object_types_config
(policy_line_id, obj_type , version)
values ('TRA','VEH','1.0');