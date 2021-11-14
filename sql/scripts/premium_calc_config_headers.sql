--combo_id guide.
--FOR OC/AC FULL ID IS FIRST 3 CHARS FROM COMBO_NAME + L -lesser_than,B -bigger_than BE -bigger_or_equal
--FOR NNW PRC1,PRC2 ETC. STANDS FOR PROTECTION CLASS I, PROTECTION CLASS II ETC;
delete from premium_calc_config_headers;
--driver age config copy once.
insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'DRI_LB','AC','driver_age','1','lesser_than','bigger_than','rise_of_premium' );

--license_age config.
insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values(
'LIC_LBE','AC','license_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values(
'LIC_L','AC','license_age','1','lesser_than'
,'rise_of_premium' );
insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values(
'LIC_BE','AC','license_age','1',
'bigger_or_equal','rise_of_premium' );

--car_age config.
insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values(
'CAR_L','AC','car_age','1','lesser_than',
'rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('CAR_LBE',
'AC','car_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('CAR_LBE',
'AC','car_age','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('CAR_BE',
'AC','car_age','1',
'bigger_or_equal','rise_of_premium' );

--mileage config.

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('MIL_LBE',
'AC','mileage','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('MIL_LBE',
'AC','mileage','1','lesser_than',
'bigger_or_equal','rise_of_premium' );
insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('MIL_BE',
'AC','mileage','1',
'bigger_or_equal','rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('MIL_L',
'AC','mileage','1','lesser_than',
'rise_of_premium' );
--accidents_count config.
insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('ACC_LBE',
'AC','accidents_count','1','lesser_than',
'bigger_or_equal','rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('ACC_L','AC','accidents_count','1','lesser_than',
'rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('ACC_BE','AC','accidents_count','1',
'bigger_or_equal','rise_of_premium' );

--NNW CONFIG

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1
)
 values('NNW_PRC1','NNW','protection_class1','1',
'rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1
)
 values('NNW_PRC2','NNW','protection_class2','1',
'rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1
)
 values('NNW_PRC3','NNW','protection_class3','1',
'rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1
)
 values('NNW_PRC4','NNW','protection_class4','1',
'rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('NNW_L',
'AC','nnw_<5','1','lesser_than',
'rise_of_premium' );

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2,header_3)
 values('NNW_LBE',
'AC','nnw_<10','1','lesser_than','bigger_or_equal',
'rise_of_premium' );	

insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1,
header_2)
 values('NNW_BE',
'AC','nnw_>=10','1','bigger_or_equal',
'rise_of_premium' );
--assistance
insert into premium_calc_config_headers
(combo_id,risk_id,combination_name,version,header_1
)
 values('ASI',
'ASSISTANCE','ASSISTANCE','1',
'rise_of_premium' );