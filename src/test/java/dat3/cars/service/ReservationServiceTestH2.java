package dat3.cars.service;

import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationServiceTestH2 {/*

    private ReservationService reservationService;
    private static ReservationRepository reservationRepository;
    private static MemberRepository memberRepository;
    private static CarRepository carRepository;

    public void setReservationService(){
        this.reservationService = new ReservationService(reservationRepository, memberRepository, carRepository);
    }

    @BeforeAll
    public static void setUpData(@Autowired ReservationRepository reservation_Repository,
                                 MemberRepository member_Repository, CarRepository car_Repository){
        reservationRepository = reservation_Repository;
        reservationRepository.deleteAll();
        memberRepository = member_Repository;
        memberRepository.deleteAll();
        carRepository = car_Repository;
        carRepository.deleteAll();
        Car c1 = new Car("Skoda", "Octavia", 500, 10);
        Car c2 = new Car("Mercedes", "220d", 700, 7);
        Member m1 = new Member("m1", "pw", "m1@a.dk", "aa", "aaa", "aaaa", "aaaa", "1234");
        Member m2 = new Member("m2", "pw", "mm@a.dk", "bb", "bbb", "bbbb", "bbbb", "1234");

        memberRepository.save(m1);
        memberRepository.save(m2);
        carRepository.save(c1);
        carRepository.save(c2);

        Reservation r1 = new Reservation(m1, c1, LocalDate.of(2022, 5, 9 ));
        Reservation r2 = new Reservation(m2, c2, LocalDate.of(2023, 10, 10 ));

        reservationRepository.save(r1);
        reservationRepository.save(r2);
    }

    @Test
    void reserveCar() {
        List<ReservationResponse> reservations = reservationService.getReservations(false);
        assertEquals(2, reservations.size());
    }

    @Test
    void getReservations() {
    }*/
}