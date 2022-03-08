delete from object_risk_config;

--1.0
insert into object_risk_config (object_type, risk_id, required, version) values ('VEH','OC','true','1.0');
insert into object_risk_config (object_type, risk_id, required, version) values ('VEH','AC','false','1.0');
insert into object_risk_config (object_type, risk_id, required, version) values ('VEH','NNW','false','1.0');
--2.0
insert into object_risk_config (object_type, risk_id, required, version) values ('VEH','OC','true','2.0');
insert into object_risk_config (object_type, risk_id, required, version) values ('VEH','AC','false','2.0');
insert into object_risk_config (object_type, risk_id, required, version) values ('VEH','NNW','false','2.0');
insert into object_risk_config (object_type, risk_id, required, version) values ('VEH','ASI','false','2.0');