CREATE TABLE if not exists participates (
   id serial PRIMARY KEY,
   auto_post_id INT NOT NULL REFERENCES auto_post(id),
   auto_user_id INT NOT NULL REFERENCES auto_user(id)
);

  comment on table participates is 'Таблица участники(для связи many-to-many: объявлений и пользователей';
  comment on column participates.id is 'Идентификатор записи';
  comment on column participates.auto_post_id is 'Идентификатор объявления';
  comment on column participates.auto_user_id is 'Идентификатор пользователя, который подписан на оповещение об изменении цены по данному объявлению';

