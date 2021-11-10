create table object_flexfields_config(
id serial not null,
TYPE varchar(40),
OBJECT_KEY varchar(40),
OBJECT_VALUE varchar(40)
)

insert into objects_flexfield_config (type,object_key,object_value)
values ('DRI','N01','Driver id in customers table.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('DRI','N02','How many years OC.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('DRI','N03','Claims count.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('DRI','D01','License date.'),

insert into objects_flexfield_config (type,object_key,object_value)
values ('CAR','C01','VIN number.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('CAR','C02','Registration number.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('CAR','N01','Vehicle id from vehicles table.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('CAR','N02','Vehicle VALUE'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('CAR','D01','Manufacturing date.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('CAR','N04','Mileage.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('CAR','N05','Amount of an insurance deposit.'),

insert into objects_flexfield_config (type,object_key,object_value)
values ('MTB','C01','VIN number.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('MTB','C02','Registration number.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('MTB','N01','Vehicle id from vehicles table.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('MTB','N02','MTB value.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('MTB','D01','Manufacturing date.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('MTB','N05','Amount of an insurance deposit.'),

insert into objects_flexfield_config (type,object_key,object_value)
values ('TRA','C01','VIN number.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('TRA','C02','Registration number.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('TRA','N01','Vehicle id from vehicles table.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('MTB','N02','TRA value.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('TRA','D01','Manufacturing date.'),
insert into objects_flexfield_config (type,object_key,object_value)
values ('TRA','N05','Amount of an insurance deposit.'),