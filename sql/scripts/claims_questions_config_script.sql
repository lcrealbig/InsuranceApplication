delete from claims_questions;

--OC
--No questions for OC
--AC
--Vehicle damage done by owner
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('1', 'AC', '3', 'Did the damage was done with purpose?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('2', 'AC', '3', 'Did driver complied with the road traffic regulations?', null, '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('3', 'AC', '3', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('4', 'AC', '3', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');

--Vehicle damage done by third party
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('5', 'AC', '4', 'Was the vehicle parked in the garage?', null, '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('6', 'AC', '4', 'Has the vehicle been parked correctly?', '5', '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('7', 'AC', '4', 'Was the vehicle parked in a high risk location?', '5', '0.95', '1', 'false', '1.0');

--Theft
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('8', 'AC', '5', 'Was the vehicle parked in the garage?', null, '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('9', 'AC', '5', 'Has the vehicle been parked correctly?', '8', '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('10', 'AC', '5', 'Was the vehicle parked in a high risk location?', '8', '0.95', '1', 'false', '1.0');

--Atmospheric phenomena
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('11', 'AC', '6', 'Was the vehicle parked in the garage?', null, '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('12', 'AC', '6', 'Has the vehicle been parked correctly?', '11', '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('13', 'AC', '6', 'Was the vehicle parked in a high risk location?', '11', '0.95', '1', 'false', '1.0');

--An accident involving animals
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('14', 'AC', '7', 'Did driver complied with the road traffic regulations?', null, '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('15', 'AC', '7', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('16', 'AC', '7', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('17', 'AC', '7', 'Has the incident been reported to the services responsible for a given section of the route?', null, '1', '0.95', 'true', '1.0');

--Random event
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('18', 'AC', '8', 'Was the vehicle parked in the garage?', null, '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('19', 'AC', '8', 'Has the vehicle been parked correctly?', '18', '1', '0.95', 'false', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('20', 'AC', '8', 'Was the vehicle parked in a high risk location?', '18', '0.95', '1', 'false', '1.0');

--ASI
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('21', 'ASI', '9', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('22', 'ASI', '9', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');

insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('23', 'ASI', '10', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('24', 'ASI', '10', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');

insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('25', 'ASI', '11', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('26', 'ASI', '11', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');

insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('27', 'ASI', '12', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('28', 'ASI', '12', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');

insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('29', 'ASI', '13', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('30', 'ASI', '13', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');
--NNW
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('31', 'NNW', '14', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('32', 'NNW', '14', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('33', 'NNW', '15', 'Was driver under the influence of drugs?', null, '1', '1', 'true', '1.0');
insert into claims_questions(question_id, risk_id, claim_type_id, question, depends_on_question_id, true_consequence_multiplier, false_consequence_multiplier, claim_rejected, version)
values ('34', 'NNW', '15', 'Has the vehicle had a valid roadworthiness test?', null, '1', '1', 'true', '1.0');