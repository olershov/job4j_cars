package ru.job4j.cars.repository.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.repository.CrudRepository;

@Repository
@AllArgsConstructor
public class HbmEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;

}
