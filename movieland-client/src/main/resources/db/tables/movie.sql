DROP TABLE IF EXISTS movie CASCADE;
CREATE TABLE movie
(
  id            SERIAL PRIMARY KEY
 ,name          VARCHAR(100)
 ,name_original VARCHAR(100)
 ,year          INTEGER
 ,description   TEXT
 ,rating        DECIMAL(2,1)
 ,price         DECIMAL(6,2)
);