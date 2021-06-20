create table users (
USER_ID number,
PASSWORD varchar2(20),
NAME varchar2(70),
ADRESS varchar2(70),
TYPE varchar2(30),
BIRTH_DATE date,
PESEL number
);
/
create sequence users_sequence
minvalue 1
maxvalue 9999999
start with 1
increment by 1;
/