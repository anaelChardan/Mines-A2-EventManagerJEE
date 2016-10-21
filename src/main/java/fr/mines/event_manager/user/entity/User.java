package fr.mines.event_manager.user.entity;

import fr.mines.event_manager.framework.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = User.tableName)
public class User extends AbstractEntity {
    final static String tableName = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "username")
    protected String username;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "email")
    protected String email;

    @Override
    public Integer getId() {
        return this.id;
    }

    public static String getTableName() {
        return tableName;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
