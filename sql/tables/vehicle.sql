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
