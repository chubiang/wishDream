CREATE TABLE "pet_breed"
(
  "breedId" integer NOT NULL,
  "breedPid" integer,
  "breedName" varchar(500) NOT NULL,
  "breedEtc" text,
  CONSTRAINT "Pet_breed_pkey" PRIMARY KEY ("breedId") 
);

INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (1, null, 'Dog', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (2, null, 'Cat', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (3, null, 'Rabbit', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (4, null, 'Bird', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (5, null, 'Lizard', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (6, null, 'Snake', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (7, null, 'Etc', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (8, 1, 'Large Dog', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (9, 1, 'Medium Dog', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (10, 1, 'Small Dog', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (11, 2, 'Short hair Cat', null);
INSERT INTO public."pet_breed"(
	"breedId", "breedPid", "breedName", "breedEtc" 
) VALUES (12, 2, 'Long hair Cat', null);

CREATE TABLE "sub_pet_breed"
(
  "subBreedId" integer NOT NULL,
  "breedId" integer NOT NULL ,
  "breedName" varchar(500) NOT NULL,
  "breedEtc" text,
  CONSTRAINT "Sub_pet_breed_pkey" PRIMARY KEY ("subBreedId"),
  CONSTRAINT "Pet_breed_fkey" FOREIGN KEY ("breedId") REFERENCES pet_breed ("breedId")
);

INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (8, 1, 'Poodle', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (8, 2, 'Greate Dane', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (8, 3, 'Doberman', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (8, 4, 'Rottweiler', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (9, 5, 'Siberian Husky', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (9, 6, 'American Eskimo', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (9, 7, 'East Siberian Laika', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (9, 8, 'Pemboke Corgi', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (9, 9, 'Sealyham Terrier', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 10, 'Maltese', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 11, 'Pomeranian', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 12, 'Toy Poodle', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 13, 'Shih Tzu', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 14, 'Mini Bichon Frise', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 15, 'Yorkshire Terrier', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 16, 'West highland White Terrie', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 17, 'Dachs Hund', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 18, 'Miniature Pinscher', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 19, 'Chihuahua', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 20, 'Boston Terrier', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 21, 'Italian Greyhound', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 22, 'Japanese Spitz', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 23, 'Pekingese', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 24, 'Pug', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 25, 'French Bulldog', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (10, 26, 'Papillon', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (11, 27, 'Abyssinian', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (11, 28, 'Sham', null);
INSERT INTO public."sub_pet_breed"(
	"breedId", "subBreedId", "breedName", "breedEtc" 
) VALUES (11, 29, 'Munchkin', null);