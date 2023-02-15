package ru.job4j.cars.service.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.car.CarRepository;
import java.util.List;
import java.util.Optional;

/**
 * Класс - реализация сервиса автомобилей
 * @see ru.job4j.cars.model.Car
 */
@AllArgsConstructor
@Service
public class HbmCarService implements CarService {

    private final CarRepository repository;

    @Override
    public Car create(Car car) {
        return repository.create(car);
    }

    @Override
    public Car update(Car car) {
        return repository.update(car);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Car> findAllOrderById() {
        return repository.findAllOrderById();
    }

    @Override
    public Optional<Car> findById(int id) {
        return repository.findById(id);
    }
}
