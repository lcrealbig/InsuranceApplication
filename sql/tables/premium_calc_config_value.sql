create SEQUENCE public.pccv_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table premium_calc_config_value (
    id integer default nextval('pccv_id_seq'),
    combo_id varchar(30),
    risk_id varchar(10),
    combination_name varchar(30),
    value_1 varchar (30),
    value_2 varchar (30),
    value_3 varchar (30),
    value_4 varchar (30),
    value_5 varchar (30),
    value_6 varchar (30),
    value_7 varchar (30),
    value_8 varchar (30),
    value_9 varchar (30),
    value_10 varchar (30),
    value_11 varchar (30),
    value_12 varchar (30),
    version varchar (30)
);
