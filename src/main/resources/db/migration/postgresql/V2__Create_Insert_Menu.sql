CREATE TABLE IF NOT EXISTS "auth"
(
    id serial PRIMARY KEY,
    role varchar NOT NULL,
    authority varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "menu_type"
(
    id serial PRIMARY KEY,
    menu_type integer NOT NULL,
    name varchar NOT NULL,
    authority varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "menu" 
(
    id serial PRIMARY KEY,
    pid integer,
    name varchar NOT NULL,
    menu_type integer NOT NULL
);

INSERT INTO public."auth"(role, authority) VALUES ('ADMIN', 'ROLE_ADMIN');
INSERT INTO public."auth"(role, authority) VALUES ('ADMIN', 'ROLE_PET_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('ADMIN', 'ROLE_DONATE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('ADMIN', 'ROLE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('ADMIN', 'ROLE_SUPER_USER');
INSERT INTO public."auth"(role, authority) VALUES ('ADMIN', 'ROLE_USER');

INSERT INTO public."auth"(role, authority) VALUES ('PET MANAGER', 'ROLE_PET_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('PET MANAGER', 'ROLE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('PET MANAGER', 'ROLE_SUPER_USER');
INSERT INTO public."auth"(role, authority) VALUES ('PET MANAGER', 'ROLE_USER');

INSERT INTO public."auth"(role, authority) VALUES ('DONATE MANAGER', 'ROLE_DONATE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('DONATE MANAGER', 'ROLE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('DONATE MANAGER', 'ROLE_SUPER_USER');
INSERT INTO public."auth"(role, authority) VALUES ('DONATE MANAGER', 'ROLE_USER');

INSERT INTO public."auth"(role, authority) VALUES ('MANAGER', 'ROLE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('MANAGER', 'ROLE_SUPER_USER');
INSERT INTO public."auth"(role, authority) VALUES ('MANAGER', 'ROLE_USER');

INSERT INTO public."auth"(role, authority) VALUES ('SUPER USER', 'ROLE_SUPER_USER');
INSERT INTO public."auth"(role, authority) VALUES ('SUPER USER', 'ROLE_USER');

INSERT INTO public."auth"(role, authority) VALUES ('USER', 'ROLE_USER');

ALTER TABLE "member" ADD COLUMN role varchar NOT NULL DEFAULT 'USER';

INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (1, 'ADMIN', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (2, 'MANAGER', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (3, 'PET', 'ROLE_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (4, 'PET MANAGER', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (5, 'DONATE', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (6, 'DONATE MANAGER', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (7, 'MARKET', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (8, 'SERVICE CENTER', 'ROLE_USER');

INSERT INTO public."menu"(name, menu_type) VALUES ('PET BOAST', 3);
INSERT INTO public."menu"(name, menu_type) VALUES ('FIND PET', 3);
INSERT INTO public."menu"(name, menu_type) VALUES ('DONATE', 5);
INSERT INTO public."menu"(name, menu_type) VALUES ('MARKET', 7);
INSERT INTO public."menu"(name, menu_type) VALUES ('SERVICE CENTER', 8);

INSERT INTO public."menu"(pid, name, menu_type) VALUES (1, 'NEWSLETTER', 3);
INSERT INTO public."menu"(pid, name, menu_type) VALUES (1, 'PHOTO ZONE', 3);
INSERT INTO public."menu"(pid, name, menu_type) VALUES (1, 'COMMUNITY', 3);

INSERT INTO public."menu"(pid, name, menu_type) VALUES (2, 'LOSE MY PET', 3);
INSERT INTO public."menu"(pid, name, menu_type) VALUES (2, 'ADOPT PET', 3);

INSERT INTO public."menu"(pid, name, menu_type) VALUES (3, 'DONATE FOR PET', 5);

INSERT INTO public."menu"(pid, name, menu_type) VALUES (4, 'DIRECT DEALING MARKET', 7);
INSERT INTO public."menu"(pid, name, menu_type) VALUES (5, 'CONTRACT', 8);
INSERT INTO public."menu"(pid, name, menu_type) VALUES (5, 'QnA', 8);