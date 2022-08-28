package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member extends UserWithRoles {

    String firstName;

    public Member(String user, String password, String email, String firstName) {
        super(user, password, email);
        this.firstName = firstName;
    }

    public Member() {
    }
}
