package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class CarRequest {

    private int id;

    private String brand;
    private String model;
    private double pricePrDay;
    private double bestDiscount;


    public static Car getCarEntity(CarRequest c) {
        return new Car(c.brand, c.model, c.pricePrDay, c.bestDiscount);
    }

    public CarRequest(Car c){
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }
}
