ALTER TABLE car ADD COLUMN brand text NOT NULL;

comment on column car.brand is 'Наименование марки авто';