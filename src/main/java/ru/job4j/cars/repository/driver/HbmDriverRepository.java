package ru.job4j.cars.repository.driver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.repository.CrudRepository;

@Repository
@AllArgsConstructor
public class HbmDriverRepository implements DriverRepository {

    private final CrudRepository crudRepository;

}
