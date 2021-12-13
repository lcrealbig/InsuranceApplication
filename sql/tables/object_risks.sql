
--InsuranceApp2021 by Marzag, Słowik, Czarny
create SEQUENCE public.or_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

CREATE TABLE public.object_risks
(
    risk_id character varying(5) COLLATE pg_catalog."default" NOT NULL,
    object_id integer NOT NULL,
    premium numeric,
    premium_for_period integer,
    is_selected character varying(5) NOT NULL,
    id integer DEFAULT nextval('or_id_seq')
);


