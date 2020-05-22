CREATE TABLE "pet_breed"
(
  "breed_id" integer NOT NULL,
  "breed_pid" integer,
  "breed_name" varchar(500) NOT NULL,
  "breed_etc" text,
  CONSTRAINT "Pet_breed_pkey" PRIMARY KEY ("breed_id") 
);

INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (1, null, 'Dog', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (2, null, 'Cat', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (3, null, 'Rabbit', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (4, null, 'Bird', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (5, null, 'Lizard', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (6, null, 'Snake', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (7, null, 'Etc', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (8, 1, 'Large Dog', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (9, 1, 'Medium Dog', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (10, 1, 'Small Dog', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (11, 2, 'Short hair Cat', null);
INSERT INTO public."pet_breed"(
	"breed_id", "breed_pid", "breed_name", "breed_etc" 
) VALUES (12, 2, 'Long hair Cat', null);

CREATE TABLE "sub_pet_breed"
(
  "sub_breed_id" integer NOT NULL,
  "breed_id" integer NOT NULL ,
  "breed_name" varchar(500) NOT NULL,
  "breed_etc" text,
  CONSTRAINT "Sub_pet_breed_pkey" PRIMARY KEY ("sub_breed_id"),
  CONSTRAINT "Pet_breed_fkey" FOREIGN KEY ("breed_id") REFERENCES pet_breed ("breed_id")
);

INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (8, 1, 'Poodle', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (8, 2, 'Greate Dane', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (8, 3, 'Doberman', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (8, 4, 'Rottweiler', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (9, 5, 'Siberian Husky', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (9, 6, 'American Eskimo', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (9, 7, 'East Siberian Laika', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (9, 8, 'Pemboke Corgi', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (9, 9, 'Sealyham Terrier', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 10, 'Maltese', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 11, 'Pomeranian', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 12, 'Toy Poodle', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 13, 'Shih Tzu', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 14, 'Mini Bichon Frise', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 15, 'Yorkshire Terrier', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 16, 'West highland White Terrie', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 17, 'Dachs Hund', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 18, 'Miniature Pinscher', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 19, 'Chihuahua', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 20, 'Boston Terrier', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 21, 'Italian Greyhound', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 22, 'Japanese Spitz', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 23, 'Pekingese', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 24, 'Pug', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 25, 'French Bulldog', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (10, 26, 'Papillon', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (11, 27, 'Abyssinian', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (11, 28, 'Sham', null);
INSERT INTO public."sub_pet_breed"(
	"breed_id", "sub_breed_id", "breed_name", "breed_etc" 
) VALUES (11, 29, 'Munchkin', null);