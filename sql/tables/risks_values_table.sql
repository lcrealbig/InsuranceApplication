create table risks_values (
id numeric,
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

--combo_id guide.
--FULL ID'S ARE MADE OF FIRST 3 CHARS FROM COMBO_NAME + L -lesser_than,B -bigger_than BE -bigger_or_equal

-- configuration for driver age.

insert into risks_values (combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values ('DRI_LB','AC', 'driver_age' , '1','27','65','400');

--license_age config.
insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
 values(
'LIC_LBE','AC','license_age','1','8',
'2','100' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,value_2)
 values(
'LIC_L','AC','license_age','1','2'
,'400' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,value_2)
 values(
'LIC_BE','AC','license_age','1',
'8','0' );

--car_age config.
insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'CAR_L','AC','car_age','1','3',
'0' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'CAR_LBE','AC','car_age','1','6',
'3','0,5%' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'CAR_LBE','AC','car_age','1','10',
'6','1%' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'CAR_BE','AC','car_age','1',
'10','2%' );

--mileage config copy 3 times.
insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3,value_4,value_5)
 values(
'MIL_LBE','AC','mileage','1','100000',
'50000','true','0,1%','0,5%');

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3,value_4,value_5)
 values(
'MIL_LBE','AC','mileage','1','350000',
'100000','true','0,5%','1%' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'MIL_L','AC','mileage','1','50000',
'0' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3,value_4)
 values(
'MIL_BE','AC','mileage','1','350000','true','1%','2%' );
--accidents_count config.
insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'ACC_LBE','AC','accidents_count','1','1,2',
'1','0,5%' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'ACC_L','AC','accidents_count','1','1',
'0' );

insert into risks_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'ACC_BE','AC','accidents_count','1',
'1,2','1,5%' );
