--file name: object_flexfield_script.sql
delete from object_flexfield_config;

insert into object_flexfield_config (type, object_key, object_value) values ('DRI', 'N01', 'Driver id in customers table.');
insert into object_flexfield_config (type, object_key ,object_value) values ('DRI', 'N02', 'How many years OC.');
insert into object_flexfield_config (type, object_key, object_value) values ('DRI', 'N03', 'Claims count.');
insert into object_flexfield_config (type, object_key, object_value) values ('DRI', 'D01', 'License date.');

insert into object_flexfield_config (type, object_key, object_value) values ('CAR', 'C01', 'VIN number.');
insert into object_flexfield_config (type, object_key, object_value) values ('CAR', 'C02', 'Registration number.');
insert into object_flexfield_config (type, object_key, object_value) values ('CAR', 'N01', 'Vehicle id from vehicles table.');
insert into object_flexfield_config (type, object_key, object_value) values ('CAR', 'N02', 'Vehicle VALUE');
insert into object_flexfield_config (type, object_key, object_value) values ('CAR', 'D01', 'Manufacturing date.');
insert into object_flexfield_config (type, object_key, object_value) values ('CAR', 'N04', 'Mileage.');
insert into object_flexfield_config (type, object_key, object_value) values ('CAR', 'N05', 'Amount of an insurance deposit.');

insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'C01', 'VIN number.');
insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'C02', 'Registration number.');
insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'N01', 'Vehicle id from vehicles table.');
insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'N02', 'MTB value.');
insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'D01', 'Manufacturing date.');
insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'N04', 'Mileage.');
insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'N05', 'Amount of an insurance deposit.');

insert into object_flexfield_config (type, object_key, object_value) values ('TRA', 'C01', 'VIN number.');
insert into object_flexfield_config (type, object_key, object_value) values ('TRA', 'C02', 'Registration number.');
insert into object_flexfield_config (type, object_key, object_value) values ('TRA', 'N01', 'Vehicle id from vehicles table.');
insert into object_flexfield_config (type, object_key, object_value) values ('MTB', 'N02', 'TRA value.');
insert into object_flexfield_config (type, object_key, object_value) values ('TRA', 'D01', 'Manufacturing date.');
insert into object_flexfield_config (type, object_key, object_value) values ('TRA', 'N05', 'Amount of an insurance deposit.');

--file name: object_risk_config_script.sql
delete from object_risk_config;

--1.0
insert into object_risk_config (object_type, risk_id, version) values ('VEH','OC','1.0');
insert into object_risk_config (object_type, risk_id, version) values ('VEH','AC','1.0');
insert into object_risk_config (object_type, risk_id, version) values ('VEH','NNW','1.0');
--2.0
insert into object_risk_config (object_type, risk_id, version) values ('VEH','OC','2.0');
insert into object_risk_config (object_type, risk_id, version) values ('VEH','AC','2.0');
insert into object_risk_config (object_type, risk_id, version) values ('VEH','NNW','2.0');
insert into object_risk_config (object_type, risk_id, version) values ('VEH','ASI','2.0');

--file name: object_type_config_script.sql
delete from object_type_config;

--1.0
insert into object_type_config (id, policy_line_type, obj_type, version) values (1, 'MOT','VEH','1.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (2, 'MOT','DRI','1.0');
--2.0
insert into object_type_config (id, policy_line_type, obj_type, version) values (3, 'MOT','VEH','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (4, 'MOT','DRI','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (5, 'TRA','VEH','2.0');

--file name: policy_line_type_config_script.sql
delete from policy_line_type_config;

--1.0
INSERT INTO policy_line_type_config (id, product_id, policy_line_type, version) VALUES (1, 'M', 'MOT', '1.0');
--2.0
INSERT INTO policy_line_type_config (id, product_id, policy_line_type, version) VALUES (2, 'M', 'MOT', '2.0');
INSERT INTO policy_line_type_config (id, product_id, policy_line_type, version) VALUES (3, 'M', 'TRA', '2.0');

--file name: premium_calc_config_header_script.sql
--combo_id guide
--FOR OC/AC FULL ID IS FIRST 3 CHARS FROM COMBO_NAME + L -lesser_than,B -bigger_than BE -bigger_or_equal
--FOR NNW PRC1,PRC2 ETC. STANDS FOR PROTECTION CLASS I, PROTECTION CLASS II ETC;
delete from premium_calc_config_header;
--1.0
--driver age config copy once.
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('DRI_LB','OC','driver_age','1.0','lesser_than','bigger_than','rise_of_premium' );

--license_age config.
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('LIC_LBE','OC','license_age','1.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('LIC_L','OC','license_age','1.0','lesser_than','rise_of_premium');
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2)
values( 'LIC_BE','OC','license_age','1.0','bigger_or_equal','rise_of_premium' );

--car_age config.
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2)
values('CAR_L','OC','car_age','1.0','lesser_than','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('CAR_LBE','OC','ar_age','1.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('CAR_LBE','OC','car_age','1.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('CAR_BE','OC','car_age','1.0','bigger_or_equal','rise_of_premium' );

--mileage config.
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('MIL_LBE','OC','mileage','1.0','lesser_than','bigger_or_equal','rise_of_premium');
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('MIL_LBE','OC','mileage','1.0','lesser_than','bigger_or_equal','rise_of_premium');
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('MIL_BE','OC','mileage','1.0','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('MIL_L','OC','mileage','1.0','lesser_than','rise_of_premium' );

--accidents_count config.
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('ACC_LBE','OC','claims_count','1.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('ACC_L','OC','claims_count','1.0','lesser_than','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('ACC_BE','OC','claims_count','1.0','bigger_or_equal','rise_of_premium' );

--NNW CONFIG
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC1','NNW','protection_class1','1.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC2','NNW','protection_class2','1.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC3','NNW','protection_class3','1.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC4','NNW','protection_class4','1.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('NNW_L','NNW','nnw_<5','1.0','lesser_than','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('NNW_LBE','NNW','nnw_<10','1.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('NNW_BE','NNW','nnw_>=10','1.0','bigger_or_equal','rise_of_premium' );

--2.0
--driver age config copy once.
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('DRI_LB','OC','driver_age','2.0','lesser_than','bigger_than','rise_of_premium' );

--license_age config.
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('LIC_LBE','OC','license_age','2.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('LIC_L','OC','license_age','2.0','lesser_than','rise_of_premium');
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2)
values( 'LIC_BE','OC','license_age','2.0','bigger_or_equal','rise_of_premium' );

--car_age config.
insert into premium_calc_config_header (combo_id,risk_id,combination_name,version,header_1,header_2)
values('CAR_L','OC','car_age','2.0','lesser_than','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('CAR_LBE','OC','ar_age','2.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('CAR_LBE','OC','car_age','2.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('CAR_BE','OC','car_age','2.0','bigger_or_equal','rise_of_premium' );

--mileage config.
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('MIL_LBE','OC','mileage','2.0','lesser_than','bigger_or_equal','rise_of_premium');
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('MIL_LBE','OC','mileage','2.0','lesser_than','bigger_or_equal','rise_of_premium');
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('MIL_BE','OC','mileage','2.0','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('MIL_L','OC','mileage','2.0','lesser_than','rise_of_premium' );

--accidents_count config.
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('ACC_LBE','OC','claims_count','2.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('ACC_L','OC','claims_count','2.0','lesser_than','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('ACC_BE','OC','claims_count','2.0','bigger_or_equal','rise_of_premium' );

--NNW CONFIG
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC1','NNW','protection_class1','2.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC2','NNW','protection_class2','2.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC3','NNW','protection_class3','2.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('NNW_PRC4','NNW','protection_class4','2.0','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('NNW_L','NNW','nnw_<5','2.0','lesser_than','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2,header_3)
values('NNW_LBE','NNW','nnw_<10','2.0','lesser_than','bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1,header_2)
values('NNW_BE','NNW','nnw_>=10','2.0','bigger_or_equal','rise_of_premium' );

--ASI
insert into premium_calc_config_header(combo_id,risk_id,combination_name,version,header_1)
values('ASI','ASSISTANCE','ASSISTANCE','2.0','rise_of_premium' );

--file name: premium_calc_config_value_script.sql
--combo_id guide.
--FULL ID'S ARE MADE OF FIRST 3 CHARS FROM COMBO_NAME + L -lesser_than,B -bigger_than BE -bigger_or_equal
delete from premium_calc_config_value;

--1.0
-- configuration for driver age.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values ('DRI_LB','OC', 'driver_age' , '1.0','27','65','400');

--license_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LIC_LBE','OC','license_age','1.0','8','2','100' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('LIC_L','OC','license_age','1.0','2','400' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('LIC_BE','OC','license_age','1.0','8','0' );

--car_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('CAR_L','OC','car_age','1.0','3','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('CAR_LBE','OC','car_age','1.0','6','3','0,5%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('CAR_LBE','OC','car_age','1.0','10','6','1%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('CAR_BE','OC','car_age','1.0','10','2%' );

--mileage config
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4)
values('MIL_LBE','OC','mileage','1.0','100000','50000','0,1%','0,5%');
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4)
values('MIL_LBE','OC','mileage','1.0','350000','100000','0,5%','1%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('MIL_L','OC','mileage','1.0','50000','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('MIL_BE','OC','mileage','1.0','350000','1%','2%' );

--accidents_count config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('ACC_LBE','OC','claims_count','1.0','1,2','1.0','0,5%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('ACC_L','OC','claims_count','1.0','1.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('ACC_BE','OC','claims_count','1.0','1,2%','1,5%' );

--NNW CONFIG
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC1','NNW','protection_class1','1.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC2','NNW','protection_class2','1.0','0,2%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC3','NNW','protection_class3','1.0','0,5%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC4','NNW','protection_class4','1.0','not_supported' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('NNW_L','NNW','nnw_<5','1.0','5','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('NNW_LBE','NNW','nnw_<10','1.0','10','5','1%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('NNW_BE','NNW','nnw_>=10','1.0','10','2%' );

--2.0
-- configuration for driver age.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values ('DRI_LB','OC', 'driver_age' , '2.0','27','65','400');

--license_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LIC_LBE','OC','license_age','2.0','8','2','100' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('LIC_L','OC','license_age','2.0','2','400' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('LIC_BE','OC','license_age','2.0','8','0' );

--car_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('CAR_L','OC','car_age','2.0','3','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('CAR_LBE','OC','car_age','2.0','6','3','0,7%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('CAR_LBE','OC','car_age','2.0','10','6','1,4%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('CAR_BE','OC','car_age','2.0','10','2,3%' );

--mileage config
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4)
values('MIL_LBE','OC','mileage','2.0','100000','50000','0,3%','0,6%');
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4)
values('MIL_LBE','OC','mileage','2.0','350000','100000','0,6%','1,3%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('MIL_L','OC','mileage','2.0','50000','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('MIL_BE','OC','mileage','2.0','350000','1,3%','2,2%' );

--accidents_count config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('ACC_LBE','OC','claims_count','2.0','1,2','2.0','0,7%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('ACC_L','OC','claims_count','2.0','2.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('ACC_BE','OC','claims_count','2.0','1,2%','1,7%' );

--NNW CONFIG
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC1','NNW','protection_class1','2.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC2','NNW','protection_class2','2.0','0,3%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC3','NNW','protection_class3','2.0','0,9%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('NNW_PRC4','NNW','protection_class4','2.0','not_supported' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('NNW_L','NNW','nnw_<5','2.0','5','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('NNW_LBE','NNW','nnw_<10','2.0','10','5','1,4%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('NNW_BE','NNW','nnw_>=10','2.0','10','2,3%' );

--ASI
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('ASI','ASSISTANCE','ASSISTANCE','2.0','150' );

--file name: product_config_script.sql
delete from product_config;
--1.0
INSERT INTO product_config (id, product_id, start_date, end_date, version) VALUES (1, 'M', '01.01.2021', '12.31.2021', '1.0');
--2.0
INSERT INTO product_config (id, product_id, start_date, end_date, version) VALUES (2, 'M', '01.01.2022', null, '2.0');

--file name: product_line_version_config_script.sql
delete from product_line_version_config;

--1.0
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (1, 'MOT', '1.0');
--2.0
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (2, 'MOT', '2.0');
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (3, 'TRA', '2.0');

--file name: vehicle_script.sql
delete from vehicle;
-- *** CARS *** --
--Volvo
--S60
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.0 i T 180KM 132kW', '132kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.0 T 180KM 132kW', '132kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.3 T5 20V 250KM 184kW', '184W', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.4 i 20V T5 260KM 191kW', '191kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.4 20V 140KM 103kW', '103kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.4 i 20V 170KM 125kW', '125kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.4 AWD 200KM 147kW', '147kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.4 T 200KM 147kW', '147kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.4 20V 210KM 154kW', '154kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.5 185KM 136kW', '136kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.5T i 20V 210KM 154kW', '154kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Petrol', '2.5 i 20V AWD R 300KM 221kW', '221kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Diesel', '2.4 TD DPF 126KM 93kW', '93kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Diesel', '2.4 D5 130KM 96kW', '96kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Diesel', '2.4 D5 163KM 120kW', '120kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'I', 'Diesel', '2.4 D5 185KM 136kW', '136kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '1.5 T3 DRIVE-E 152KM 112kW', '112kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '1.6 T3 150KM 110kW', '110kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '1.6 T4 180KM 132kW', '132kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '2.0 T3 DRIVE-E 152KM 112kW', '112kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '2.0 T4 DRIVE-E 190KM 140kW', '140kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '2.0 T5 245KM 180kW', '180kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '2.0 T5 DRIVE-E 245KM 180kW', '180kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '2.0 T 203KM 149kW', '149kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '2.0 T5 240KM 177kW', '177kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '2.5 T5 254KM 187kW', '187kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Petrol', '3.0 T6 304KM 224kW', '224kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '1.6 D2 115KM 85kW', '85kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D2 DRIVE-E 120KM 88kW', '88kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D3 DRIVE-E 150KM 110kW', '110kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D4 DRIVE-E 181KM 133kW', '133kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D4 DRIVE-E 190KM 140kW', '140kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D5 DRIVE-E 225KM 165kW', '165kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D3 136KM 100kW', '100kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D3 163KM 120kW', '120kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.0 D4 163KM 120kW', '120kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.4 D4 190KM 140kW', '140kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.4 D4 DRIVE-E 190KM 140kW', '140kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'II', 'Diesel', '2.4 D5 215KM 158kW', '158kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'III', 'Petrol', '2.0 T4 190KM 140kW', '140kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'III', 'Petrol', '2.0 T5 250KM 184kW', '184kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'III', 'Petrol', '2.0 T5 254KM 187kW', '187kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'III', 'Petrol', '2.0 T6 310KM 228kW', '228kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'III', 'Hybrid', '2.0 B4 Mild Hybrid 211KM 155kW', '155kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'III', 'Hybrid', '2.0 B5 Mild Hybrid 264KM 194kW', '194kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S60', 'III', 'Hybrid', '2.0 T8 Twin Engine Plug-In Hybrid 405KM 298kW', '298kW', 'I', 'true');
--S90
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'I', 'Petrol', '3.0 24V 204KM 150kW', '150kW', 'II', 'false');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'I', 'Petrol', '2.0 T5 250KM 184kW', '184kW', 'II', 'false');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Petrol', '2.0 T5 254KM 187kW', '187kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Petrol', '2.0 T6 310KM 228kW', '228kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Petrol', '2.0 T6 320KM 235kW', '235kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Petrol', '2.0 T8 407KM 299kW', '299kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Diesel', '2.0 D4 190KM 140kW', '140kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Diesel', '2.0 D5 235KM 173kW', '173kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Diesel', '2.0 D3 150KM 110kW', '110kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Hybrid', '2.0 T8 390KM 287kW', '287kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Hybrid', '2.0 T8 Plug-In Hybrid 391KM 288kW', '288kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Volvo', 'S90', 'II', 'Hybrid', '2.0 T8 Twin Engine 391KM 288kW', '288kW', 'I', 'true');

--Honda
--NSX
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'NSX', 'I', 'Petrol', '3.0 24V 273KM 201kW', '201kW', 'III', 'false');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES('CAR', 'Honda', 'NSX', 'I', 'Petrol', '3.2 24V 280KM 206kW', '206kW', 'III', 'false');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'NSX', 'I', 'Petrol', '3.0 24V Automatic 256KM 188kW', '188kW', 'III', 'false');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'NSX', 'II', 'Hybrid', '3.5 V6 573KM 421kW', '421kW', 'III', 'false');
--CR-V
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'I', 'Petrol', '2.0 16V RD1 128KM 94kW', '94kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'I', 'Petrol', '2.0 16V 147KM 108kW', '108kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'II', 'Petrol', '2.0 16V 150KM 110kW', '110kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'II', 'Petrol', '2.4 i 16V LX 162KM 119kW', '119kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'II', 'Diesel', '2.2 TD 140KM 103kW', '103kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'III', 'Petrol', '2.0 i-VTEC 150KM 110kW', '110kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'III', 'Petrol', '2.4 i-VTEC 170KM 125kW', '125kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'III', 'Diesel', '2.2 i-CDTi 150KM 110kW', '110kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'III', 'Diesel', '2.2 i-CDTi 140KM 103kW', '103kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'IV', 'Petrol', '2.0 i-VTEC 155KM 114kW', '114kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'IV', 'Petrol', '2.4 188KM 138kW', '138kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'IV', 'Petrol', '2.4 185KM 136kW', '136kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'IV', 'Diesel', '1.6 i-DTEC 120KM 88kW', '88kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'IV', 'Diesel', '1.6 i-DTEC 160KM 118kW', '118kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'IV', 'Diesel', '2.2 i-DTEC 150KM 110kW', '110kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'V', 'Petrol', '1.5 VTEC TURBO 173KM 127kW', '127kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'V', 'Petrol', '1.5 VTEC TURBO 193KM 142kW', '142kW', 'I', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('CAR', 'Honda', 'CR-V', 'V', 'Petrol', '2.0 i-MMD 184KM 135kW', '135kW', 'I', 'true');

-- *** MOTORBIKES *** --
--Honda
--CBF
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBF', 'I', 'Petrol', '599cc liquid-cooled 4-stroke 16-valve', '71kW', 'III', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBF', 'II', 'Petrol', '599cc liquid-cooled 4-stroke 16-valve', '70kW', 'III', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBF', 'III', 'Petrol', '599cc liquid-cooled 4-stroke 16-valve', '70kW', 'III', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBF', 'IV', 'Petrol', '599cc liquid-cooled 4-stroke 16-valve', '71kW', 'III', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBF', 'V', 'Petrol', '599cc liquid-cooled 4-stroke 16-valve', '76kW', 'III', 'true');
--CBR
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBR', 'I', 'Petrol', '599 cc liquid-cooled inline four-cylinder', '80kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBR', 'II', 'Petrol', '599 cc liquid-cooled inline four-cylinder', '79kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBR', 'III', 'Petrol', '599 cc liquid-cooled inline four-cylinder', '79kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBR', 'IV', 'Petrol', '599 cc liquid-cooled inline four-cylinder', '74kW', 'II', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('MTB', 'Honda', 'CBR', 'V', 'Petrol', '599 cc liquid-cooled inline four-cylinder', '75kW', 'II', 'true');

-- *** TRAILERS *** --
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('TRA', 'Besttrailers', 'Loader', 'null', 'null', 'null', 'null', 'null', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('TRA', 'EMTECH', 'PC24', 'null', 'null', 'null', 'null', 'null', 'true');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('TRA', 'Fendt', 'OPAL 550', 'null', 'null', 'null', 'null', 'null', 'false');
INSERT INTO vehicle (VEHICLE_TYPE, BRAND, VEHICLE_MODEL, GENERATION, ENGINE_TYPE, ENGINE, POWER, PROTECTION_CLASS, PARTS_AVAILABILITY) VALUES ('TRA', 'Nooteboom', 'OBV-55-03v', 'null', 'null', 'null', 'null', 'null', 'true');


--file name: vehicle_type_config_script.sql
delete from vehicle_type_config;

--1.0
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (1, 'MOT','MTB', '1.0');
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (2, 'MOT','CAR', '1.0');
--2.0
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (3, 'MOT','MTB', '2.0');
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (4, 'MOT','CAR', '2.0');
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (5, 'TRA', 'TRA', '2.0');

