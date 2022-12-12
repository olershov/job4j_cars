package ru.job4j.cars.repository.driver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.repository.CrudRepository;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmDriverRepository implements DriverRepository {

    private final CrudRepository crudRepository;

    @Override
    public boolean create(Driver driver) {
        return false;
    }

    @Override
    public boolean update(Driver driver) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Driver> findAllOrderById() {
        return null;
    }

    @Override
    public Optional<Driver> findById(int id) {
        return Optional.empty();
    }
}
