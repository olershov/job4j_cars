package ru.job4j.cars.repository.engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.config.TestHbmConfig;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HbmEngineRepositoryTest {

    private final TestHbmConfig testHbmConfig = new TestHbmConfig();
    private final CrudRepository crudRepository = testHbmConfig.getCrudRepository();
    private final HbmEngineRepository engineRepository = new HbmEngineRepository(crudRepository);

    @AfterEach
    public void wipeTable() {
       testHbmConfig.wipeTable("DELETE from Engine");
    }

    @Test
    public void whenCreateAndFindById() {
        Engine engine = new Engine(0, "Test");
        engineRepository.create(engine);
        Engine result = engineRepository.findById(engine.getId()).get();
        assertThat(result.getName()).isEqualTo(engine.getName());
    }

    @Test
    public void whenUpdate() {
        Engine engine = new Engine(0, "Test");
        engine = engineRepository.create(engine);
        engine.setName("Test2");
        engineRepository.update(engine);
        Engine result = engineRepository.findById(engine.getId()).get();
        assertThat(result.getName()).isEqualTo(engine.getName());
    }

    @Test
    public void whenDelete() {
        Engine engine = new Engine(0, "Test");
        engineRepository.create(engine);
        engineRepository.delete(engine.getId());
        Optional<Engine> result = engineRepository.findById(engine.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void whenFindAll() {
        Engine engine = new Engine(0, "Test");
        Engine engine2 = new Engine(0, "Test2");
        engineRepository.create(engine);
        engineRepository.create(engine2);
        assertEquals(engineRepository.findAllOrderById(), List.of(engine, engine2));
    }
}