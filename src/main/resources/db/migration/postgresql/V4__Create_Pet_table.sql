-- Table: public.pet

-- DROP TABLE public.pet;

CREATE SEQUENCE pet_seq;

CREATE TABLE public.pet
(
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    pet_id integer NOT NULL DEFAULT nextval('pet_seq'),
    pet_name character varying COLLATE pg_catalog."default" NOT NULL,
    pet_age integer,
    sub_breed_id integer NOT NULL,
    pet_gender character varying COLLATE pg_catalog."default" NOT NULL,
    pet_etc text COLLATE pg_catalog."default",
    CONSTRAINT pet_pkey PRIMARY KEY (pet_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pet
    OWNER to wishdream;

GRANT ALL ON TABLE public.pet TO wishdream;