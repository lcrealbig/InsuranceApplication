
--InsuranceApp2021 by Marzag, Słowik, Czarny
create SEQUENCE public.or_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

CREATE TABLE public.object_risks
(
    risk_id character varying(5) COLLATE pg_catalog."default" NOT NULL,
    object_no integer NOT NULL,
    premium numeric,
    premium_for_period integer,
    is_selected boolean NOT NULL,
    id integer NOT NULL DEFAULT nextval('or_id_seq')
);


