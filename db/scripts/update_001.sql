CREATE TABLE if not exists auto_user (
  id SERIAL PRIMARY KEY,
  login text NOT NULL UNIQUE,
  password text NOT NULL UNIQUE
);

CREATE TABLE if not exists auto_post (
  id SERIAL PRIMARY KEY,
  text VARCHAR NOT NULL,
  created timestamp,
  auto_user_id int NOT NULL references auto_user(id)
);
