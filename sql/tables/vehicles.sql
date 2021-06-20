create table vehicles(
VEHICLE_ID number,
MFG_YEAR number,     --year of manufacturing
BRAND varchar2(30),
MODEL varchar2(30),
ENGINE varchar2(15),
POWER number
);
/
create sequence vehicles_sequence
minvalue 1
maxvalue 9999999
start with 1
increment by 1;
/