package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.repository.CrudRepository;

@Repository
@AllArgsConstructor
public class HbmCarRepository implements CarRepository {

    private final CrudRepository crudRepository;
}
