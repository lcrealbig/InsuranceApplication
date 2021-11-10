--InsuranceApp2021 by Marzag, Słowik, Czarny

create SEQUENCE public.orc_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

create table object_risks_config (
id integer default nextval('orc_id_seq'),
object_type varchar(10),
object_risks varchar(30),
version varchar(30)
);
--CONFIG just for testing premium calculation
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','OC','1');
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','AC','1');
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','NNW','1');
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','ASI','1');
