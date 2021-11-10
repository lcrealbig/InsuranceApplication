create table premium_calculation_config_values (
id serial,
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

insert into premium_calculation_config_values (combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values ('DRI_LB','AC', 'driver_age' , '1','27','65','400');

--license_age config.
insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
 values(
'LIC_LBE','AC','license_age','1','8',
'2','100' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,value_2)
 values(
'LIC_L','AC','license_age','1','2'
,'400' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,value_2)
 values(
'LIC_BE','AC','license_age','1',
'8','0' );

--car_age config.
insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'CAR_L','AC','car_age','1','3',
'0' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'CAR_LBE','AC','car_age','1','6',
'3','0,5%' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'CAR_LBE','AC','car_age','1','10',
'6','1%' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'CAR_BE','AC','car_age','1',
'10','2%' );

--mileage config copy 3 times.
insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3,value_4)
 values(
'MIL_LBE','AC','mileage','1','100000',
'50000','0,1%','0,5%');

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3,value_4)
 values(
'MIL_LBE','AC','mileage','1','350000',
'100000','0,5%','1%' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'MIL_L','AC','mileage','1','50000',
'0' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'MIL_BE','AC','mileage','1','350000','1%','2%' );
--accidents_count config.
insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values(
'ACC_LBE','AC','accidents_count','1','1,2',
'1','0,5%' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'ACC_L','AC','accidents_count','1','1',
'0' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values(
'ACC_BE','AC','accidents_count','1',
'1,2','1,5%' );

--NNW CONFIG

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1
)
 values('NNW_PRC1','NNW','protection_class1','1',
'0' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1
)
 values('NNW_PRC2','NNW','protection_class2','1',
'0,2%' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1
)
 values('NNW_PRC3','NNW','protection_class3','1',
'0,5%' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1
)
 values('NNW_PRC4','NNW','protection_class4','1',
'not_supported' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values('NNW_L',
'NNW','nnw_<5','1','5',
'0' );

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2,value_3)
 values('NNW_LBE',
'NNW','nnw_<10','1','10','5',
'1%' );	

insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1,
value_2)
 values('NNW_BE',
'NNW','nnw_>=10','1','10',
'2%' );

--asistance
insert into premium_calculation_config_values
(combo_id,risk_id,combination_name,version,value_1
)
 values('ASI',
'ASSISTANCE','nnw_>=10','1',
'150' );

