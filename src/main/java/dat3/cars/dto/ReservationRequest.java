package dat3.cars.dto;


import dat3.cars.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    private String username;
    private int carId;
    private String date;

    public static Reservation reservationEntity(ReservationRequest r){
        //return new Reservation(r.getUsername(), r.getCarId(), r.getDate());
        return null;
    }


}
