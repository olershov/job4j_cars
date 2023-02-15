package ru.job4j.cars.repository.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс - реализация хранилища двигателей
 * @see ru.job4j.cars.model.Engine
 */
@Repository
@AllArgsConstructor
public class HbmEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;
    private static final String BY_ID = " WHERE id = :fId";
    private static final String UPDATE = "UPDATE Engine SET name = :fName" + BY_ID;
    private static final String DELETE = "DELETE Engine" + BY_ID;
    private static final String FIND_ALL = "from Engine";

    @Override
    public Engine create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }

    @Override
    public Engine update(Engine engine) {
        crudRepository.run(
                UPDATE, Map.of("fName", engine.getName(), "fId", engine.getId()));
        return engine;
    }

    @Override
    public void delete(int id) {
        crudRepository.run(DELETE, Map.of("fId", id));
    }

    @Override
    public List<Engine> findAllOrderById() {
        return crudRepository.query(
                FIND_ALL, Engine.class
        );
    }

    @Override
    public Optional<Engine> findById(int id) {
        return crudRepository.optional(
                FIND_ALL + BY_ID, Engine.class,
                Map.of("fId", id)
        );
    }
}
