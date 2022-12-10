CREATE TABLE if not exists driver(
    id serial primary key,
    name text NOT NULL,
    user_id int NOT NULL UNIQUE references auto_user(id)
);

comment on table driver is 'Таблица водителей';
comment on column driver.id is 'Идентификатор водителя';
comment on column driver.name is 'Имя водителя';
comment on column driver.user_id is 'Идентификатор профиля пользователя данного водителя';