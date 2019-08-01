CREATE TABLE "Member"
(
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    etc text COLLATE pg_catalog."default",
    birth date NOT NULL,
    "joinDate" date NOT NULL,
    "leaveDate" date,
    CONSTRAINT "Member_pkey" PRIMARY KEY (email)
)