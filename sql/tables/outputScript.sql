--file name: bill.sql
create SEQUENCE public.bill_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table bill (
    id integer default nextval('bill_id_seq'),
    bill_status varchar(30),
    victim_id INTEGER,
    bill_amount numeric,
    amount_to_withdraw numeric
);

--file name: claim.sql
create SEQUENCE public.claim_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table claim (
    id integer default nextval('claim_id_seq'),
    policy_id integer,
    status varchar(30),
    claim_type varchar(30),
    claim_description varchar(2000),
    claim_date date,
    last_status_update date,
    event_place varchar(200)
);

--file name: customer.sql
create SEQUENCE public.cust_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table customer(
    id integer default nextval('cust_id_seq'),
    name varchar(150),
    pesel varchar(11),
    address varchar(150),
    birth_date date,
    phone_num bigint
);

--file name: insured_object.sql
create SEQUENCE public.io_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table insured_object(
    id integer default nextval('io_id_seq') ,
    policy_line_id integer,
    transaction_id integer,
    type varchar(40),
    c01 varchar(40),
    c02 varchar(40),
    c03 varchar(40),
    c04 varchar(40),
    n01 integer,
    n02 integer,
    n03 integer,
    n04 integer,
    n05 integer,
    n06 integer,
    n07 integer,
    d01 date,
    d02 date,
    d03 date
);

--file name: object_flexfield_config.sql
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

--file name: object_risk.sql
create SEQUENCE public.or_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

CREATE TABLE public.object_risk(
    id integer DEFAULT nextval('or_id_seq'),
    risk_id character varying(5) COLLATE pg_catalog."default" NOT NULL,
    object_id integer NOT NULL,
    premium numeric,
    premium_for_period numeric,
    is_selected character varying(5) NOT NULL
);

--file name: object_risk_config.sql
create SEQUENCE public.orc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table object_risk_config (
    id integer default nextval('orc_id_seq'),
    object_type varchar(10),
    risk_id varchar(30),
    version varchar(30)
);


--file name: object_type_config.sql
create table object_type_config (
    id integer,
    policy_line_type varchar(5),
    obj_type varchar(5),
    version varchar(5)
);

--file name: policy.sql
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

--file name: policy_line.sql
create SEQUENCE public.line_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table policy_line(
    id integer default nextval('line_id_seq'),
    transaction_id integer,
    policy_id integer,
    policy_line_type varchar(30)
);



--file name: policy_line_type_config.sql
create table policy_line_type_config (
    id integer,
    product_id varchar(5),
    policy_line_type varchar(5),
    version varchar(5)
);

--file name: premium_calc_config_header.sql
create SEQUENCE public.pcch_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table premium_calc_config_header (
    id integer default nextval('pcch_id_seq'),
    combo_id varchar(30),
    risk_id varchar(10),
    combination_name varchar(30),
    header_1 varchar(30),
    header_2 varchar(30),
    header_3 varchar(30),
    header_4 varchar(30),
    header_5 varchar(30),
    header_6 varchar(30),
    header_7 varchar(30),
    header_8 varchar(30),
    header_9 varchar(30),
    header_10 varchar(30),
    header_11 varchar(30),
    header_12 varchar(30),
    version varchar(30)
);

--file name: premium_calc_config_value.sql
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

--file name: product_config.sql
create table product_config (
    id integer,
    product_id varchar(5),
    start_date date,
    end_date date,
    version varchar(5)
);

--file name: product_line_version_config.sql
create table product_line_version_config (
    id integer,
    policy_line_type varchar(5),
    version varchar(5)
);

--file name: transaction.sql
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

--file name: user.sql
create SEQUENCE public.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table users (
    user_id varchar(20) default nextval('user_id_seq'),
    password varchar(20),
    name varchar(70),
    address varchar(70),
    type varchar(30),
    birth_date date,
    pesel varchar(11)
);

--file name: vehicle.sql
CREATE SEQUENCE public.veh_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table vehicle(
    id integer default nextval('veh_id_seq'),
    vehicle_type varchar(25),
    brand varchar(40),
    vehicle_model varchar(40),
    generation varchar(30),
    engine_type varchar(25),
    engine varchar(55),
    power varchar(15),
    protection_class varchar(10),
    parts_availability varchar(5)
);

--file name: vehicle_type_config.sql
create table vehicle_type_config (
    id integer,
    policy_line_type varchar(10),
    vehicle_type varchar(10),
    version varchar(5)
);

--file name: victim.sql
create SEQUENCE public.victim_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table victim(
    id integer default nextval('victim_id_seq'),
    name varchar(150),
    pesel varchar(11),
    address varchar(150),
    birth_date date,
    phone_num bigint
);


