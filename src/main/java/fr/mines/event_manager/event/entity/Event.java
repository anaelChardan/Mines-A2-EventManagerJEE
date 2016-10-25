package fr.mines.event_manager.event.entity;

import fr.mines.event_manager.framework.entity.AbstractSelfManagedEntity;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = Event.tableName)
public class Event extends AbstractSelfManagedEntity {
    final static String tableName = "event";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "description", nullable = false)
    protected String description;

    @Column(name = "start_date", nullable = false)
    protected Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    protected Date endDate;

    @Column(name = "max_tickets", nullable = false)
    protected Integer maxTickets;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", nullable = false)
    protected User author;

    @Embedded
    @Column(nullable = false)
    protected Address address;

    @Column(name = "price")
    protected Double price = 0.00;

    @Column(name = "is_published")
    protected Boolean published = false;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "event_user",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    protected Set<User> subscribers = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public static String getTableName() {
        return tableName;
    }

    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Event setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Event setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getMaxTickets() {
        return maxTickets;
    }

    public Event setMaxTickets(Integer maxTickets) {
        this.maxTickets = maxTickets;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Event setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Event setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Event setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public Event setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public Event addSubscribers(User user)
    {
        System.out.println("coucou");
        this.subscribers.add(user);

        return this;
    }
}
