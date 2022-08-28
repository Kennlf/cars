package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @BeforeAll
    public static void setUp(@Autowired CarRepository carRepository){
        Car c1 = new Car("Skoda", "corolla", 500, 30);
        Car c2 = new Car("Lexus", "LFA", 1000, 5);
        carRepository.save(c1);
        carRepository.save(c2);
    }

    @Test
    public void findCarByBrand() {
        List<Car> cars = carRepository.findCarByBrand("Lexus");
        assertEquals(1, cars.size());
    }

    @Test
    public void findCarByPricePrDayBetween() {
        List<Car> cars = carRepository.findCarByPricePrDayBetween(0, 500);
        assertEquals(1, cars.size());

        cars = carRepository.findCarByPricePrDayBetween(501, 1100);
        assertEquals(1, cars.size());

        cars = carRepository.findCarByPricePrDayBetween(0, 1001);
        assertEquals(2, cars.size());
    }
}