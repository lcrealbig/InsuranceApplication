--combo_id guide.
--FULL ID'S ARE MADE OF FIRST 3 CHARS FROM COMBO_NAME + L -lesser_than,B -bigger_than BE -bigger_or_equal
delete from premium_calc_config_value;

--1.0
-- configuration for driver age.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values ('LB','OC', 'driver_age' , '1.0','27','65','400');

--license_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','license_age','1.0','8','2','100' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','license_age','1.0','2','400' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','OC','license_age','1.0','8','0' );

--car_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','car_age','1.0','3','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','car_age','1.0','6','3','0,5%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','car_age','1.0','10','6','1%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','OC','car_age','1.0','10','2%' );

--mileage config
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4)
values('LBE','OC','mileage','1.0','100000','50000','0,1%','0,5%');
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4)
values('LBE','OC','mileage','1.0','350000','100000','0,5%','1%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','mileage','1.0','50000','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('BE','OC','mileage','1.0','350000','1%','2%' );

--accidents_count config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','claims_count','1.0','1.2','1.0','0,5%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','claims_count','1.0','1.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','OC','claims_count','1.0','1.2','1,5%' );

--NNW CONFIG
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC1','NNW','protection_class1','1.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC2','NNW','protection_class2','1.0','0,2%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC3','NNW','protection_class3','1.0','0,5%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC4','NNW','protection_class4','1.0','not_supported' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','NNW','nnw_<5','1.0','5','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','NNW','nnw_<10','1.0','10','5','1%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','NNW','nnw_>=10','1.0','10','2%' );

--2.0
-- configuration for driver age.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values ('LB','OC', 'driver_age' , '2.0','27','65','400');

--license_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','license_age','2.0','8','2','100' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','license_age','2.0','2','400' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','OC','license_age','2.0','8','0' );

--car_age config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','car_age','2.0','3','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','car_age','2.0','6','3','0,7%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','car_age','2.0','10','6','1,4%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','OC','car_age','2.0','10','2,3%' );

--mileage config
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4,value_5,value_6)
values('LBE','OC','mileage','2.0','100000','50000','0,3%','0,6%','1%','2%');
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4,value_5,value_6)
values('LBE','OC','mileage','2.0','350000','100000','0,6%','1,3%','2%','5%');
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','mileage','2.0','50000','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3,value_4,value_5)
values('BE','OC','mileage','2.0','350000','1,3%','2,2%','2,5%','7%' );

--accidents_count config.
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','OC','claims_count','2.0','1,2','2.0','0,7%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','OC','claims_count','2.0','2.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','OC','claims_count','2.0','1,2','1,7%' );

--NNW CONFIG
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC1','NNW','protection_class1','2.0','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC2','NNW','protection_class2','2.0','0,3%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC3','NNW','protection_class3','2.0','0,9%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('PRC4','NNW','protection_class4','2.0','not_supported' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('L','NNW','nnw_<5','2.0','5','0' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2,value_3)
values('LBE','NNW','nnw_<10','2.0','10','5','1,4%' );
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1,value_2)
values('BE','NNW','nnw_>=10','2.0','10','2,3%' );

--ASI
insert into premium_calc_config_value(combo_id,risk_id,combination_name,version,value_1)
values('ASI','ASSISTANCE','ASSISTANCE','2.0','150' );