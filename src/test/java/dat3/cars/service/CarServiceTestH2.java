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

    private CarService carService;
    private static CarRepository carRepository;
    private static int carId1, carId2;


    @BeforeAll
    public static void setUpData(@Autowired CarRepository car_Repository){
        carRepository = car_Repository;
        carRepository.deleteAll();
        Car c1 = new Car("Skoda", "Octavia", 500, 10);
        Car c2 = new Car("Mercedes", "220d", 700, 7);
        carRepository.save(c1);
        carRepository.save(c2);
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
        assertThat(cars, containsInAnyOrder(hasProperty("brand", is("Skoda")), hasProperty("brand", is("Mercedes"))));
        assertNull(cars.get(0).getBestDiscount());
    }

    @Test
    void findCarById() throws Exception {
        CarResponse response = carService.findCarById(carId1, false);
        assertEquals(carId1, response.getId());
        assertNull(response.getBestDiscount());
        assertNull(response.getCreated());
    }

    @Test
    void findCarByIdWithFullInfo() throws Exception {
        CarResponse response = carService.findCarById(carId1,true);
        assertEquals(carId1, response.getId());
        assertEquals(10, response.getBestDiscount());
        assertNotNull(response.getCreated());
    }

    @Test
    void addCar() {
        CarRequest request = new CarRequest("Audi","A6",750,5);
        CarResponse response = carService.addCar(request, true);
        assertTrue(response.getId() > 0);
        assertTrue(response.getCreated().isBefore(LocalDateTime.now()));
    }


    @Test
    void editCar() throws Exception {
        Car carToEditEntity = carRepository.findById(carId1).get();
        LocalDateTime prevEdited = carToEditEntity.getLastEdited();
        carToEditEntity.setPricePrDay(999);
        carToEditEntity.setBestDiscount(99);
        CarRequest request = new CarRequest(carToEditEntity);
        carService.editCar(request,carId1);

        Car edited = carRepository.findById(carId1).get();
        assertEquals(999, edited.getPricePrDay());
        assertEquals(99, edited.getBestDiscount());
        assertTrue(edited.getLastEdited().isBefore(LocalDateTime.now()));
    }

    @Test
    void setPricePrDay() throws Exception {
        carService.setPricePrDay(carId1, 9);

        CarResponse response = carService.findCarById(carId1, false);
        assertEquals(9, response.getPricePrDay());
    }


    @Test
    void setDiscountForCar() throws Exception {
        carService.setDiscountForCar(carId1, 999);

        CarResponse response = carService.findCarById(carId1, true);
        assertEquals(999, response.getBestDiscount());
    }

    @Test
    void deleteById() {
    }
}