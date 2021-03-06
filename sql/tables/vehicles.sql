CREATE SEQUENCE public.veh_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table vehicles(
VEHICLE_ID int default nextval('veh_id_seq'),
VEHICLE_TYPE varchar(25),
BRAND varchar(40),
VEHICLE_MODEL varchar(40),
GENERATION varchar(30),
ENGINE_TYPE varchar(25),
ENGINE varchar(55)
);
/