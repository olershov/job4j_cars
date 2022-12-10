CREATE TABLE if not exists engine(
    id serial primary key,
    name text NOT NULL
);

comment on table engine is 'Таблица двигатели';
comment on column engine.id is 'Идентификатор двигателя';
comment on column engine.name is 'Наименование двигателя';
