create SEQUENCE public.pltc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy_line_types_config (
ID integer default nextval('pltc_id_seq'),
PRODUCT_ID varchar(5),
POLICY_LINE_ID varchar(5),
VERSION varchar(5)
);
--config
insert into policy_line_types_config
(product_id,policy_line_id,version)
values ('M','MOT','1.0')
insert into policy_line_types_config
(product_id,policy_line_id,version)
values ('M','TRA','1.0');