package dat3.cars.entity;


import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String brand;

    @Column(length = 50, nullable = false)
    private String model;

    private double pricePrDay;

    private double bestDiscount;

   @CreationTimestamp
    private LocalDateTime created;

   @UpdateTimestamp
   private LocalDateTime LastEdited;

   @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
   private List<Reservation> reservations = new ArrayList<>();

   public void addReservation(Reservation reservation){
       reservations.add(reservation);
       reservation.setCar(this);
   }

    public Car(String brand, String model, double pricePrDay, double bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
