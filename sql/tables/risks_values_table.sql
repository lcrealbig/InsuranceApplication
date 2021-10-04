create table risks_values (
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
-- configuration for driver age.

insert into risks_values (risk_id,combination_name,version,value_1,value_2)
values ('AC', 'driver_age' , '1','27','65');

--license_age config.
insert into risks_values
(risk_id,combination_name,version,value_1,value_2,value_3)
 values(
'AC','license_age','1','8',
'2','100' );

insert into risks_values
(risk_id,combination_name,version,value_1,value_2)
 values(
'AC','license_age','1','2'
,'400' );

insert into risks_values
(risk_id,combination_name,version,value_1,value_2)
 values(
'AC','license_age','1',
'8','0' );

--car_age config.
insert into risks_values
(risk_id,combination_name,version,value_1,
value_2)
 values(
'AC','car_age','1','3',
'0' );

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'AC','car_age','1','6',
'3','0,5%' );

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'AC','car_age','1','10',
'6','1%' );

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2)
 values(
'AC','car_age','1',
'10','2%' );

--mileage config copy 3 times.
insert into risks_values
(risk_id,combination_name,version,value_1,
value_2,value_3,value_4,value_5,value_6)
 values(
'AC','mileage','1','100000',
'50000','yes','yes','0,1%','0,5%');

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2,value_3,value_4,value_5,value_6)
 values(
'AC','mileage','1','350000',
'100000','yes','yes','0,5%','1%' );

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2)
 values(
'AC','license_age','1','50000',
'0' );

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2,value_3,value_4,value_5)
 values(
'AC','license_age','1','350000','yes','yes','1%','2%' );
--accidents_count config.
insert into risks_values
(risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'AC','accidents_count','1','1,2',
'1','0,5%' );

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2)
 values(
'AC','accidents_count','1','1',
'0' );

insert into risks_values
(risk_id,combination_name,version,value_1,
value_2)
 values(
'AC','accidents_count','1',
'1,2','1,5%' );
