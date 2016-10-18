package fr.mines.event_manager.user.entity;

import fr.mines.event_manager.global.entity.AbstractEntity;

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
}
