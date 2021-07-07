--create sequence first to use it as a value for id.
CREATE SEQUENCE public.veh_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table vehicles(
VEHICLE_ID int default nextval('veh_id_seq'),
MFG_YEAR int,
BRAND varchar(30),
MODEL varchar(30),
ENGINE varchar(15),
POWER int
);

/
