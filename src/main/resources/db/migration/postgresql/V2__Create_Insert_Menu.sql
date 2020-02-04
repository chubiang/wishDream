CREATE TABLE IF NOT EXISTS "auth"
(
    id serial PRIMARY KEY,
    authority varchar NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "menu_type"
(
    id serial PRIMARY KEY,
    menuType integer NOT NULL,
    name varchar NOT NULL,
    authority varchar NOT NULL REFERENCES auth(authority)
);

CREATE TABLE IF NOT EXISTS "menu" 
(
    id serial PRIMARY KEY,
    pid serial,
    name varchar NOT NULL,
    menuType integer NOT NULL
);

INSERT INTO public."auth"(authority) VALUES ('ROLE_ADMIN');
INSERT INTO public."auth"(authority) VALUES ('ROLE_PET_MANAGER');
INSERT INTO public."auth"(authority) VALUES ('ROLE_DONATE_MANAGER');
INSERT INTO public."auth"(authority) VALUES ('ROLE_MANAGER');
INSERT INTO public."auth"(authority) VALUES ('ROLE_SUPER_USER');
INSERT INTO public."auth"(authority) VALUES ('ROLE_PASS_USER');
INSERT INTO public."auth"(authority) VALUES ('ROLE_USER');

ALTER TABLE "member" ADD COLUMN authority varchar NOT NULL DEFAULT 'ROLE_USER';
ALTER TABLE "member" ADD CONSTRAINT FK_MEMBER_AUTH FOREIGN KEY(authority) REFERENCES auth(authority);

INSERT INTO public."menu_type"(menuType, name, authority) VALUES (1, 'PET', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (1, 'PET', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (1, 'PET', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (1, 'PET', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (1, 'PET', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (1, 'PET', 'ROLE_PASS_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (1, 'PET', 'ROLE_USER');

INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'DONATE', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'DONATE', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'DONATE', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'DONATE', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'DONATE', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'DONATE', 'ROLE_PASS_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'DONATE', 'ROLE_USER');

INSERT INTO public."menu_type"(menuType, name, authority) VALUES (3, 'MARKET', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (3, 'MARKET', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (3, 'MARKET', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (3, 'MARKET', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (3, 'MARKET', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'MARKET', 'ROLE_PASS_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (3, 'MARKET', 'ROLE_USER');

INSERT INTO public."menu_type"(menuType, name, authority) VALUES (4, 'SERVICE CENTER', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (4, 'SERVICE CENTER', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (4, 'SERVICE CENTER', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (4, 'SERVICE CENTER', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (4, 'SERVICE CENTER', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (2, 'SERVICE CENTER', 'ROLE_PASS_USER');
INSERT INTO public."menu_type"(menuType, name, authority) VALUES (4, 'SERVICE CENTER', 'ROLE_USER');

INSERT INTO public."menu"(name, menuType) VALUES ('PET BOAST', 1);
INSERT INTO public."menu"(name, menuType) VALUES ('FIND PET', 1);
INSERT INTO public."menu"(name, menuType) VALUES ('DONATE', 2);
INSERT INTO public."menu"(name, menuType) VALUES ('MARKET', 3);
INSERT INTO public."menu"(name, menuType) VALUES ('SERVICE CENTER', 4);

INSERT INTO public."menu"(pid, name, menuType) VALUES (1, 'NEWSLETTER', 1);
INSERT INTO public."menu"(pid, name, menuType) VALUES (1, 'PHOTO ZONE', 1);
INSERT INTO public."menu"(pid, name, menuType) VALUES (1, 'COMMUNITY', 1);

INSERT INTO public."menu"(pid, name, menuType) VALUES (2, 'LOSE MY PET', 1);
INSERT INTO public."menu"(pid, name, menuType) VALUES (2, 'ADOPT PET', 1);

INSERT INTO public."menu"(pid, name, menuType) VALUES (3, 'DONATE FOR PET', 1);

INSERT INTO public."menu"(pid, name, menuType) VALUES (4, 'DIRECT DEALING MARKET', 3);
INSERT INTO public."menu"(pid, name, menuType) VALUES (5, 'CONTRACT', 4);
INSERT INTO public."menu"(pid, name, menuType) VALUES (5, 'QnA', 4);