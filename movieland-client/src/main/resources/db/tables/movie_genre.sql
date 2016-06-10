DROP TABLE IF EXISTS movie_genre;
CREATE TABLE movie_genre
(
  movie_id INTEGER REFERENCES movie (id)
 ,genre_id INTEGER REFERENCES genre (id)
);