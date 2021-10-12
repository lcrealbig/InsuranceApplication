--table risks_headers
create table risks_headers (
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

--driver age config copy once.
insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'AC','driver_age','1','lesser_than','bigger_than','rise_of_premium' );

--license_age config.
insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'AC','license_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2)
 values(
'AC','license_age','1','lesser_than'
,'rise_of_premium' );
insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2)
 values(
'AC','license_age','1',
'bigger_or_equal','rise_of_premium' );

--car_age config.
insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2)
 values(
'AC','car_age','1','lesser_than',
'rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'AC','car_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'AC','car_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2)
 values(
'AC','car_age','1',
'bigger_or_equal','rise_of_premium' );

--mileage config.

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3,header_4,header_5)
 values(
'AC','mileage','1','lesser_than',
'bigger_or_equal','on_list','absent','rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3,header_4,header_5)
 values(
'AC','mileage','1','lesser_than',
'bigger_or_equal','on_list','absent','rise_of_premium' );
insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3,header_4)
 values(
'AC','mileage','1',
'bigger_or_equal','on_list','absent','rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2)
 values(
'AC','mileage','1','lesser_than',
'rise_of_premium' );
--accidents_count config.
insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'AC','accidents_count','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2)
 values(
'AC','accidents_count','1','lesser_than',
'rise_of_premium' );

insert into risks_headers
(risk_id,combination_name,version,header_1,
header_2)
 values(
'AC','accidents_count','1',
'bigger_or_equal','rise_of_premium' );