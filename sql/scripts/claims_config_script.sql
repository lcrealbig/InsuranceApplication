delete from claims_config;
--OC
insert into claims_config(id, risk_id, claim_type, version)
values ('1', 'OC', 'Traffic damage', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('2', 'OC', 'Traffic damage - total loss', '1.0');
--AC
insert into claims_config(id, risk_id, claim_type, version)
values ('3', 'AC', 'Vehicle damage done by owner', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('4', 'AC', 'Vehicle damage done by third party', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('5', 'AC', 'Theft', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('6', 'AC', 'Atmospheric phenomena', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('7', 'AC', 'Accident involving animals', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('8', 'AC', 'Random event', '1.0');
--ASI
insert into claims_config(id, risk_id, claim_type, version)
values ('9', 'ASI', 'Towing the vehicle (up to 400km)', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('10', 'ASI', 'Fuel delivery in canisters', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('11', 'ASI', 'Replacement vehicle', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('12', 'ASI', 'Supply of spare parts', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('13', 'ASI', 'Wheel change', '1.0');
--NNW
insert into claims_config(id, risk_id, claim_type, version)
values ('14', 'NNW', 'Permanent health impairment', '1.0');
insert into claims_config(id, risk_id, claim_type, version)
values ('15', 'NNW', 'Death', '1.0');