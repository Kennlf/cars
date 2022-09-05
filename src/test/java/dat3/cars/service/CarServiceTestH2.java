package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CarServiceTestH2 {

    public CarService carService;
    public static CarRepository carRepository;
    private static int carId1, carId2;


    @BeforeAll
    public static void setUpData(@Autowired CarRepository car_Repository){
        carRepository = car_Repository;
        carRepository.deleteAll();
        Car c1 = new Car("Skoda", "Octavia", 500, 10);
        Car c2 = new Car("Mercedes", "220d", 700, 7);
        c1 = carRepository.save(c1);
        c2 = carRepository.save(c2);
        carId1 = c1.getId();
        carId2 = c2.getId();
    }

    @BeforeEach
    public void setCarSerivce(){
        this.carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
        List<CarResponse> cars = carService.getCars(false);
        assertEquals(2, cars.size());
        //assertThat(response, containsInAnyOrder(hasProperty("brand", is("Skoda")), hasProperty("brand", is("Mercedes"))));
    }
/*
    @Test
    void findCarById() throws Exception {
        CarResponse response = carService.findCarById(carId1, false);
        assertEquals(carId1, response.getId());
        assertEquals(10, response.getBestDiscount());
        assertNotNull(response.getCreated());
    }

 */

    //Hvad går galt - Hvorfor kan den ikke bare laves i en CarRequest uden først at laves som car
    // Metoden virker heller ikke
    /*
    @Test
    void addCar() {
        Car c = new Car("Audi","A6",750,5);
        CarRequest request = new CarRequest(c);
        CarResponse response = carService.addCar(request, true);
        assertTrue(response.getId() > 0);
        assertTrue(response.getCreated().isBefore(LocalDateTime.now()));
    } */


    @Test
    void editCar() throws Exception {
    }


    @Test
    void setDiscountForCar() {
    }

    @Test
    void deleteById() {
    }
}