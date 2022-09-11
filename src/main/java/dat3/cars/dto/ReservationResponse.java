package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ReservationResponse {

    private int resId;
    private LocalDateTime reservationDate;
    private String memberUsername;
    private LocalDate rentalDate;
    private int carId;
    private String carBrand;
    private String carModel;



    public ReservationResponse(Reservation reservation, boolean includeAll){
        this.memberUsername = reservation.getMember().getUsername();
        this.carId = reservation.getCar().getId();
        this.carBrand = reservation.getCar().getBrand();
        this.carModel = reservation.getCar().getModel();
        this.rentalDate = reservation.getRentalDate();
        if(includeAll){
            this.resId = reservation.getId();
            this.reservationDate = reservation.getReservationDate();

        }
    }


}
