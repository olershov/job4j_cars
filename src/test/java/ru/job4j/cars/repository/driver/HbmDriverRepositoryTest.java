package ru.job4j.cars.repository.driver;

import org.junit.jupiter.api.*;
import ru.job4j.cars.config.TestHbmConfig;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.user.HbmUserRepository;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class HbmDriverRepositoryTest {

    private static final TestHbmConfig TEST_HBM_CONFIG = new TestHbmConfig();
    private static final CrudRepository CRUD_REPOSITORY = TEST_HBM_CONFIG.getCrudRepository();
    private static final HbmUserRepository USER_REPOSITORY = new HbmUserRepository(CRUD_REPOSITORY);
    private final HbmDriverRepository driverRepository = new HbmDriverRepository(CRUD_REPOSITORY);
    private static User user = new User(0, "login", "password");
    private static User user2 = new User(0, "login2", "password2");

    @BeforeAll
    public static void fillAssociatedTables() {
        user = USER_REPOSITORY.create(user);
        user2 = USER_REPOSITORY.create(user2);
    }

    @AfterEach
    public void wipeTable() {
        TEST_HBM_CONFIG.wipeTable("DELETE from Driver");
    }

    @AfterAll
    public static void wipeAssociatedTables() {
        TEST_HBM_CONFIG.wipeTable("DELETE from User");
    }

    @Test
    public void whenCreateAndFindById() {
        Driver driver = new Driver(0, "name", user);
        driverRepository.create(driver);
        Driver result = driverRepository.findById(driver.getId()).get();
        assertThat(result.getName()).isEqualTo(driver.getName());
    }

    @Test
    public void whenUpdate() {
        Driver driver = new Driver(0, "name", user);
        driverRepository.create(driver);
        driver.setUser(user2);
        driverRepository.update(driver);
        Driver result = driverRepository.findById(driver.getId()).get();
        assertThat(result.getUser().getLogin()).isEqualTo(user2.getLogin());
    }

    @Test
    public void whenDelete() {
        Driver driver = new Driver(0, "name", user);
        driverRepository.create(driver);
        driverRepository.delete(driver.getId());
        Optional<Driver> result = driverRepository.findById(driver.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void whenFindAll() {
        Driver driver = new Driver(0, "name", user);
        Driver driver2 = new Driver(0, "name2", user2);
        driverRepository.create(driver);
        driverRepository.create(driver2);
        assertEquals(driverRepository.findAllOrderById(), List.of(driver, driver2));
    }
}