package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmCarRepository implements CarRepository {

    private final CrudRepository crudRepository;

    @Override
    public boolean create(Car car) {
        return false;
    }

    @Override
    public boolean update(Car car) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Car> findAllOrderById() {
        return null;
    }

    @Override
    public Optional<Car> findById(int id) {
        return Optional.empty();
    }
}
