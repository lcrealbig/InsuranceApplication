-- Table: public.object_risks

-- DROP TABLE public.object_risks;

CREATE TABLE public.object_risks
(
    risk_id character varying(5) COLLATE pg_catalog."default" NOT NULL,
    object_no integer NOT NULL,
    premium numeric,
    premium_for_period integer,
    is_selected boolean NOT NULL,
    id integer NOT NULL DEFAULT nextval('risks_calculation_sums_id_seq'::regclass)
)


--only for backend testing purpouses.
insert into object_risks (risk_id,object_no,is_selected)
values ('AC',28,'yes'),
insert into object_risks (risk_id,object_no,is_selected)
values ('OC',28,'yes'),
insert into object_risks (risk_id,object_no,is_selected)
values ('NNW',28,'yes'),
insert into object_risks (risk_id,object_no,is_selected)
values ('ASI',28,'yes')