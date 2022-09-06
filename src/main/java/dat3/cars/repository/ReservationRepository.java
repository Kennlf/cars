package dat3.cars.repository;

import dat3.cars.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Boolean existsByCar_IdAndRentalDate(int carId, LocalDate rentalDate);
}
