package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceTestMockitoTests {

    @Mock
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @BeforeEach
    public void setup(){
        carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of(
                new Car("Skoda", "Octavia", 500, 10),
                new Car("Mercedes", "220d", 700, 7)));
        List<CarResponse> cars = carService.getCars(false);
        assertEquals(2, cars.size());
    }

    @Test
    void findCarById() throws Exception {
        Car c = new Car("Audi", "A6", 750, 5);
        c.setId(1000);
        Mockito.when(carRepository.findById(1000)).thenReturn(Optional.of(c));
        CarResponse response = carService.findCarById(1000, false);
        assertEquals(750, response.getPricePrDay());
        assertEquals("Audi", response.getBrand());
    }

    @Test
    void addCar() {
        Car c = new Car("Audi", "A6", 750, 5);
        c.setId(99);
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(c);
        CarRequest carRequest = new CarRequest(c);
        CarResponse found = carService.addCar(carRequest, false);
        assertEquals("Audi", found.getBrand());
        //assertEquals(99, found.getId());
    }

    @Test // is this correct?
    void editCar() throws Exception {
        Car c = new Car("Audi", "A6", 750, 5);
        c.setId(99);
        CarRequest request = new CarRequest(c);
        Mockito.when(carRepository.findById(99)).thenReturn(Optional.of(c));
        CarResponse response = carService.findCarById(99, false);

    }


    @Test
    void setDiscountForCar() {
    }

    @Test
    void deleteById() {
    }
}