package ua.ita.smartcarservice.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@MappedSuperclass
//public class UserBaseEntity {
//@Inheritance
public abstract class UserBaseEntity {

    protected UserBaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(length = 100, nullable = false, unique = true)
    protected String email;

    //TODO Make ono-to-one with login
    @Column(length = 100, nullable = false)
    protected String password;

    @Column(length = 100, nullable = false)
    protected String fullName;

    //TODO Make ono-to-one with login

    @Column(length = 100, nullable = false, unique = true)
    protected String userName;

    public UserBaseEntity(String email, String password, String fullName, String userName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.userName = userName;
    }
}
