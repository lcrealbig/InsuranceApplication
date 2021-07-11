create table insured_objects(
OBJECT_ID number,
POLICY_LINE_NO number,
TYPE varchar2(30),
C01 varchar2(30),
C02 varchar2(30),
C03 varchar2(30),
C04 varchar2(30),
N01 number,
N02 number,
N03 number,
N04 number,
D01 date,
D02 date,
D03 date
);
/
create sequence insured_objects_sequence
minvalue 1
maxvalue 9999999
start with 1
increment by 1;
/