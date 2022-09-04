package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> response = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());
        return response;
    }

    public CarResponse findCarById(int id) throws Exception{
        Car found = carRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        return new CarResponse(found, false);
    }

    public CarResponse addCar(CarRequest carRequest){
        if(carRepository.existsById(carRequest.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID already exists");
        }

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, false);
    }

    public void editCar(CarRequest body, int id){
        Car car = carRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID already exists"));
        if(!(body.getId() == (id))){ // is this can't find car? because id doens't match?
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't change Id");
        }

        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
    }

    public void setDiscountForCar(int id, double value){
        Car car = carRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id does not exists"));
        car.setBestDiscount(value);
        carRepository.save(car);
    }

    public void deleteById(int id){
        carRepository.deleteById(id);
    }

}
