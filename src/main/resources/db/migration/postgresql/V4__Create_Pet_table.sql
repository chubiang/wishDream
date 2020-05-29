-- Table: public.pet

-- DROP TABLE public.pet;

CREATE SEQUENCE pet_id_seq start 1;

CREATE TABLE pet_sequence (
    id integer NOT NULL DEFAULT nextval('pet_id_seq')
);

ALTER SEQUENCE pet_id_seq
OWNED BY public.pet_sequence.id;

CREATE TABLE public.pet
(
    pet_id integer NOT NULL DEFAULT nextval('pet_sequence'::regclass),
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