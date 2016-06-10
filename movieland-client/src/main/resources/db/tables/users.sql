DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
  id       SERIAL PRIMARY KEY
 ,name     VARCHAR(100)
 ,email    VARCHAR(100)
 ,password VARCHAR(10)
);