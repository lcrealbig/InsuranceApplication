create table policies(
POLICY_ID number,
OWNER_ID number,
TYPE varchar2(50),
STATUS varchar2(40),
START_DATE date,
END_DATE date,
POLICY_TYPE varchar2(40),
ALT_NO varchar2(40)
);
/
create sequence policies_sequence
minvalue 1
maxvalue 9999999
start with 1
increment by 1;
/