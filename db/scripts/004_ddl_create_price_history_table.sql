  CREATE TABLE if not exists price_history(
     id SERIAL PRIMARY KEY,
     before BIGINT not null,
     after BIGINT not null,
     created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
     auto_post_id INT NOT NULL REFERENCES auto_post(id)
  );

  comment on table price_history is 'Таблица историй цен';
  comment on column price_history.id is 'Идентификатор истории';
  comment on column price_history.before is 'Цена до изменения';
  comment on column price_history.after is 'Цена после изменения';
  comment on column price_history.created is 'Время обновления цены';
  comment on column price_history.auto_post_id is 'Идентификатор объявления';
