CREATE TABLE if not exists car(
    id serial primary key,
    name text NOT NULL,
    engine_id int NOT NULL UNIQUE references engine(id),
    driver_id int NOT NULL references driver(id)
);

comment on table car is 'Таблица автомобилей';
comment on column car.id is 'Идентификатор авто';
comment on column car.name is 'Наименование авто';
comment on column car.engine_id is 'Идентификатор двигателя авто';
comment on column car.driver_id is 'Идентификатор текущего владельца авто';
