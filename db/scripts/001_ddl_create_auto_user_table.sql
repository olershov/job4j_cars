CREATE TABLE if not exists auto_user (
  id SERIAL PRIMARY KEY,
  login text NOT NULL UNIQUE,
  password text NOT NULL UNIQUE
);

comment on table auto_user is 'Таблица пользователей';
comment on column auto_user.id is 'Идентификатор пользователя';
comment on column auto_user.login is 'Логин';
comment on column auto_user.password is 'Пароль';

