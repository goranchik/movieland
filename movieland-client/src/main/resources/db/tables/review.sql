DROP TABLE IF EXISTS review;
CREATE TABLE review
(
  movie_id    INTEGER REFERENCES movie (id)
 ,reviewer_id INTEGER REFERENCES users (id)
 ,feedback    TEXT
);