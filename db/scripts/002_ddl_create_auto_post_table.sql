
CREATE TABLE if not exists auto_post (
  id SERIAL PRIMARY KEY,
  text VARCHAR NOT NULL,
  created timestamp,
  auto_user_id int NOT NULL references auto_user(id)
);

comment on table auto_post is 'Таблица объявлений';
comment on column auto_post.id is 'Идентификатор объявления';
comment on column auto_post.created is 'Время создания';
comment on column auto_post.auto_user_id is 'Идентификатор пользователя, опубликовавшего объявление';