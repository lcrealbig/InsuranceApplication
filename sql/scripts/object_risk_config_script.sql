delete from object_risk_config;

--1.0
insert into object_risk_config (object_type, risk_id, required, deposit_amount, version) values ('VEH','OC','true','5210000','1.0');
insert into object_risk_config (object_type, risk_id, required, deposit_amount, version) values ('VEH','AC','false','5210000','1.0');
insert into object_risk_config (object_type, risk_id, required, deposit_amount, version) values ('VEH','NNW','false', '50000','1.0');
--2.0
insert into object_risk_config (object_type, risk_id, required, deposit_amount, version) values ('VEH','OC','true','5210000','2.0');
insert into object_risk_config (object_type, risk_id, required, deposit_amount, version) values ('VEH','AC','false','5210000','2.0');
insert into object_risk_config (object_type, risk_id, required, deposit_amount, version) values ('VEH','NNW','false','50000','2.0');
insert into object_risk_config (object_type, risk_id, required, deposit_amount, version) values ('VEH','ASI','false','200000','2.0');