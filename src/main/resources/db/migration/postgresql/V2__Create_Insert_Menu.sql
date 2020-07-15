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
    name varchar NOT NULL,
    menu_type integer NOT NULL,
    menu_order integer NOT NULL
);

INSERT INTO public."auth"(role, authority) VALUES ('ADMIN', 'ROLE_ADMIN');
INSERT INTO public."auth"(role, authority) VALUES  ('MANAGER', 'ROLE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('PET MANAGER', 'ROLE_PET_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('DONATE MANAGER', 'ROLE_DONATE_MANAGER');
INSERT INTO public."auth"(role, authority) VALUES ('SUPER USER', 'ROLE_SUPER_USER');
INSERT INTO public."auth"(role, authority) VALUES ('USER', 'ROLE_USER');

INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (1, 'ADMIN', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (2, 'PET', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (3, 'PET MANAGER', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (4, 'DONATE', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (5, 'DONATE MANAGER', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (6, 'MARKET', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (7, 'SERVICE CENTER', 'ROLE_ADMIN');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (8, 'NEWSLETTER', 'ROLE_ADMIN');

INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (2, 'PET', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (3, 'PET MANAGER', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (4, 'DONATE', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (5, 'DONATE MANAGER', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (6, 'MARKET', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (7, 'SERVICE CENTER', 'ROLE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (8, 'NEWSLETTER', 'ROLE_MANAGER');

INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (2, 'PET', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (3, 'PET MANAGER', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (4, 'DONATE', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (6, 'MARKET', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (7, 'SERVICE CENTER', 'ROLE_PET_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (8, 'NEWSLETTER', 'ROLE_PET_MANAGER');

INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (2, 'PET', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (4, 'DONATE', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (5, 'DONATE MANAGER', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (6, 'MARKET', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (7, 'SERVICE CENTER', 'ROLE_DONATE_MANAGER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (8, 'NEWSLETTER', 'ROLE_DONATE_MANAGER');

INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (2, 'PET', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (4, 'DONATE', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (6, 'MARKET', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (7, 'SERVICE CENTER', 'ROLE_SUPER_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (8, 'NEWSLETTER', 'ROLE_SUPER_USER');

INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (2, 'PET', 'ROLE_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (4, 'DONATE', 'ROLE_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (6, 'MARKET', 'ROLE_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (7, 'SERVICE CENTER', 'ROLE_USER');
INSERT INTO public."menu_type"(menu_type, name, authority) VALUES (8, 'NEWSLETTER', 'ROLE_USER');

INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('PET BOAST', 2, 1);
INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('MOVIE ZONE', 2, 2);
INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('FIND PET', 2, 3);
INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('ADOPT PET', 2, 4);

INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('DONATE FOR PET', 4, 1);
INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('DIRECT DEALING MARKET', 6, 1);
INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('COMMUNITY', 6, 2);

INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('QnA', 7, 1);
INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('CONTRACT', 7, 2);
INSERT INTO public."menu"(name, menu_type, menu_order) VALUES ('NEWSLETTER', 8, 1);

