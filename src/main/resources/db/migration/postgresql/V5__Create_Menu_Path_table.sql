CREATE TABLE MENU_PATH(
  id serial PRIMARY KEY,
  name varchar NOT NULL UNIQUE,
  path varchar NOT NULL UNIQUE
);

INSERT INTO MENU_PATH (name, path) VALUES('PET BOAST', '/pet/boast');
INSERT INTO MENU_PATH (name, path) VALUES('MOVIE ZONE', '/pet/movie');
INSERT INTO MENU_PATH (name, path) VALUES('FIND PET', '/pet/find');
INSERT INTO MENU_PATH (name, path) VALUES('ADOPT PET', '/pet/adopt');
INSERT INTO MENU_PATH (name, path) VALUES('DONATE FOR PET', '/pet/donate');
INSERT INTO MENU_PATH (name, path) VALUES('DIRECT DEALING MARKET', '/market/direct');
INSERT INTO MENU_PATH (name, path) VALUES('COMMUNITY', '/market/community');
INSERT INTO MENU_PATH (name, path) VALUES('QnA', '/help/qna');
INSERT INTO MENU_PATH (name, path) VALUES('CONTRACT', '/help/map');
INSERT INTO MENU_PATH (name, path) VALUES('NEWSLETTER', '/news');

ALTER TABLE MENU ADD CONSTRAINT menu_name_unique UNIQUE (name) ;