package ru.job4j.cars.repository.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;

    @Override
    public boolean create(Engine engine) {
        return false;
    }

    @Override
    public boolean update(Engine engine) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Engine> findAllOrderById() {
        return null;
    }

    @Override
    public Optional<Engine> findById(int id) {
        return Optional.empty();
    }
}
