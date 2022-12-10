create table owner_history(
    id serial primary key,
    driver_id int NOT NULL references driver(id),
    car_id int NOT NULL references car(id),
    start_at timestamp NOT NULL,
    end_at timestamp NOT NULL
);

comment on table owner_history is 'Таблица для связи many-to-many авто и водителей';
comment on column owner_history.id is 'Идентификатор записи';
comment on column owner_history.driver_id is 'Идентификатор водителя';
comment on column owner_history.car_id is 'Идентификатор автомобиля';
comment on column owner_history.start_at is 'Дата начала владения';
comment on column owner_history.end_at is 'Дата окончания владения';
