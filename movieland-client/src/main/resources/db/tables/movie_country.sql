DROP TABLE IF EXISTS movie_country;
CREATE TABLE movie_country
(
  movie_id   INTEGER REFERENCES movie (id)
 ,country_id INTEGER REFERENCES country (id)
);