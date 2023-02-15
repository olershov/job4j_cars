package ru.job4j.cars.service.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.engine.EngineRepository;
import java.util.List;
import java.util.Optional;

/**
 * Класс - реализация сервиса двигателей
 * @see ru.job4j.cars.model.Engine
 */
@AllArgsConstructor
@Service
public class HbmEngineService implements EngineService {

    private final EngineRepository repository;

    @Override
    public Engine create(Engine engine) {
        return repository.create(engine);
    }

    @Override
    public Engine update(Engine engine) {
        return repository.update(engine);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Engine> findAllOrderById() {
        return repository.findAllOrderById();
    }

    @Override
    public Optional<Engine> findById(int id) {
        return repository.findById(id);
    }
}
