package dat3.cars.configuration;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDate;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        passwordUsedByAll = "test12";
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        //String user, String password, String email, String firstName, String lastName,
        // String street, String city, int zip, boolean approved, int ranking
        Member m1 = new Member("Userxx", passwordUsedByAll, "a@b.dk", "Harry", "Potter",
                "Hogwarts", "Imagination","0000");
        memberRepository.save(m1);
        setupUserWithRoleUsers();
        Car c1 = new Car("Skoda", "Karoq", 550, 0.10);
        carRepository.save(c1);
        LocalDate date = LocalDate.of(2022,9,6);

        Reservation newRes = new Reservation(m1,c1,date);
        Reservation newRes2 = new Reservation(m1,c1,LocalDate.of(2023,6,9));
        Reservation newRes3 = new Reservation(m1,c1,LocalDate.of(2023,2,10));
        Reservation newRes4 = new Reservation(m1,c1,LocalDate.of(2023,12,29));
        m1.addReservation(newRes);
       // m1.addReservation(newRes2);
       // m1.addReservation(newRes3);
       // m1.addReservation(newRes4);
        memberRepository.save(m1);
        c1.addReservation(newRes);
        //reservationRepository.save(newRes);



    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}
