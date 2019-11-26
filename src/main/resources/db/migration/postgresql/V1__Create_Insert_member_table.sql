CREATE TABLE "member"
(
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE  pg_catalog."default" NOT NULL,
    etc text COLLATE pg_catalog."default",
    birth date NOT NULL,
    "joinDate" date NOT NULL,
    "leaveDate" date,
    CONSTRAINT "Member_pkey" PRIMARY KEY (email)
);

INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('nana', 'nana@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2012-02-10', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('berry', 'berry@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2015-04-15', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('rosa', 'rosa42@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2016-01-22', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('kevien', 'kev99@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2010-10-25', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('toby', 'toby@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('1998-12-24', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('ran', 'ran05@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2012-11-17', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('weston', 'chill@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2008-06-02', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('wendy', 'wd0742@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2001-08-09', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('fooba', 'babo@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2009-09-19', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);
INSERT INTO public."member"(
	username, email, password, etc, birth, "joinDate", "leaveDate")
	VALUES ('yammy', 'yam@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('1997-05-08', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null);

