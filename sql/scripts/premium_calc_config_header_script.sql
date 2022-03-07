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