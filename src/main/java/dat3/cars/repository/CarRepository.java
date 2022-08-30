package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findCarByBrand(String brand);
    List<Car> findCarByPricePrDayBetween(double start, double min);

}
