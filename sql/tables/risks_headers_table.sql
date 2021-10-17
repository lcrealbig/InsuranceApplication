create table risks_headers (
id numeric,
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
--combo_id guide.
--FULL ID IS FIRST 3 CHARS FROM COMBO_NAME + L -lesser_than,B -bigger_than BE -bigger_or_equal

--driver age config copy once.
insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'DRI_LB','AC','driver_age','1','lesser_than','bigger_than','rise_of_premium' );

--license_age config.
insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'LIC_LBE','AC','license_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values(
'LIC_L','AC','license_age','1','lesser_than'
,'rise_of_premium' );
insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values(
'LIC_BE','AC','license_age','1',
'bigger_or_equal','rise_of_premium' );

--car_age config.
insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values(
'CAR_L','AC','car_age','1','lesser_than',
'rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('CAR_LBE',
'AC','car_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('CAR_LBE',
'AC','car_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('CAR_BE',
'AC','car_age','1',
'bigger_or_equal','rise_of_premium' );

--mileage config.

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3,header_4,header_5)
 values('MIL_LBE',
'AC','mileage','1','lesser_than',
'bigger_or_equal','on_list','absent','rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3,header_4,header_5)
 values('MIL_LBE',
'AC','mileage','1','lesser_than',
'bigger_or_equal','on_list','absent','rise_of_premium' );
insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3,header_4)
 values('MIL_BE',
'AC','mileage','1',
'bigger_or_equal','on_list','absent','rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('MIL_L',
'AC','mileage','1','lesser_than',
'rise_of_premium' );
--accidents_count config.
insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('ACC_LBE',
'AC','accidents_count','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('ACC_L','AC','accidents_count','1','lesser_than',
'rise_of_premium' );

insert into risks_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('ACC_BE','AC','accidents_count','1',
'bigger_or_equal','rise_of_premium' );

/*
select v.combination_name, header_1,value_1,header_2,value_2, header_3,value_3,header_4,value_4, header_5,value_5,header_6,value_6, header_7,value_7,header_8,value_8, v.version from risks_headers h,risks_values v 
where v.combination_name = h.combination_name 
and v.version = h.version
and v.combination_name = 'license_age'
--and h.header_id = 'LIC_AGE_LBE'
AND V.HEADER_ID = H.HEADER_ID;
*/