CREATE TABLE IF NOT EXISTS "member"
(
    username character varying(30) NOT NULL,
    email character varying(100) NOT NULL,
    password text NOT NULL,
    etc text,
    birth date,
    join_date date NOT NULL,
    leave_date date,
    role character varying(100) default 'ROLE_USER' NOT NULL,
    CONSTRAINT "Member_pkey" PRIMARY KEY (email)
);

INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('nana', 'nana@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2012-02-10', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'ADMIN');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('berry', 'berry@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2015-04-15', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'MANAGER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('rosa', 'rosa42@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2016-01-22', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'PET_MANAGER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('kevien', 'kev99@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2010-10-25', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'DONATE_MANAGER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('toby', 'toby@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('1998-12-24', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'USER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('ran', 'ran05@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2012-11-17', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'SUPER_USER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('weston', 'chill@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2008-06-02', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'USER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('wendy', 'wd0742@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2001-08-09', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'USER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('fooba', 'babo@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('2009-09-19', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'USER');
INSERT INTO public."member"(
	username, email, password, etc, birth, join_date, leave_date, role)
	VALUES ('yammy', 'yam@gmail.com', '$2a$10$C2AxRosFuTrPBFvMTTEeheXjWN1YpAmVzn0kTZ6nBhj.TuLITR11i', null, to_date('1997-05-08', 'YYYY-MM-DD'), to_date('2019-08-01', 'YYYY-MM-DD'), null, 'USER');

