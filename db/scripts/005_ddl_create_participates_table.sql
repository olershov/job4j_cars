CREATE TABLE if not exists participates (
   id serial PRIMARY KEY,
   auto_post_id INT NOT NULL REFERENCES auto_post(id),
   auto_user_id INT NOT NULL REFERENCES auto_user(id)
);