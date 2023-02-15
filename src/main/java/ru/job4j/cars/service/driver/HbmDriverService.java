package ru.job4j.cars.service.driver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.repository.driver.DriverRepository;
import java.util.List;
import java.util.Optional;

/**
 * Класс - реализация сервиса водителей
 * @see ru.job4j.cars.model.Driver
 */
@AllArgsConstructor
@Service
public class HbmDriverService implements DriverService {

    private final DriverRepository repository;

    @Override
    public Driver create(Driver driver) {
        return repository.create(driver);
    }

    @Override
    public Driver update(Driver driver) {
        return repository.update(driver);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Driver> findAllOrderById() {
        return repository.findAllOrderById();
    }

    @Override
    public Optional<Driver> findById(int id) {
        return repository.findById(id);
    }
}
