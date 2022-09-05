package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.repository.CarRepository;
import dat3.cars.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars(false);
    }

    @GetMapping("/admin")
    public List<CarResponse> getCarsWithAllInfo(){
        return carService.getCars(true);
    }

    @GetMapping("/{carId}")
    public CarResponse getCarById(@PathVariable int carId) throws Exception {
        CarResponse response = carService.findCarById(carId,false);
        return response;

    }

    @GetMapping(path = "/admin/{carId}")
    public CarResponse getCarByIdWithAllInfo(@PathVariable int carId) throws Exception {
        CarResponse response = carService.findCarById(carId,true);
        return response;
    }

    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body, true);
    }
    @PutMapping("/{carId}")
    public void editCar(@RequestBody CarRequest body, @PathVariable int carId){
        carService.editCar(body, carId);
    }


    @PatchMapping("/rentalprice/{carId}/{newPrice}")
    public void setNewPrice(@PathVariable int carId, @PathVariable double newPrice){
        carService.setPricePrDay(carId, newPrice);
    }

    @PatchMapping("/discount/{carId}/{newDiscount}")
    public void setNewDiscount(@PathVariable int carId, @PathVariable double newDiscount){
        carService.setDiscountForCar(carId, newDiscount);
    }




}
