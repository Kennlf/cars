package dat3.cars.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDateTime reservationDate;

    private LocalDate rentalDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Car car;


    public Reservation(Member member, Car car, LocalDate rentalDate) {
        this.member = member;
        this.car = car;
        this.rentalDate = rentalDate;
    }
}
