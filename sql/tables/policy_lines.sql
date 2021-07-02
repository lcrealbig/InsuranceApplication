create table policy_lines(
LINE_ID number,
POLICY_NO number,
PRODUCT_LINE_TYPE varchar2(30)
);
/
create sequence policy_lines_sequence
minvalue 1
maxvalue 9999999
start with 1
increment by 1;
/