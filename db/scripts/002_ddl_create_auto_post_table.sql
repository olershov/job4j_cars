
CREATE TABLE if not exists auto_post (
  id SERIAL PRIMARY KEY,
  text VARCHAR NOT NULL,
  created timestamp,
  auto_user_id int NOT NULL references auto_user(id)
);