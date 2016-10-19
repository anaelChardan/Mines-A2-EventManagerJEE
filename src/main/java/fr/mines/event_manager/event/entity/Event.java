package fr.mines.event_manager.event.entity;

import fr.mines.event_manager.framework.entity.AbstractEntity;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = Event.getTableName())
public class Event extends AbstractEntity {
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

    @Embedded
    @Column(nullable = false)
    protected Address address;

    @Column(name = "price")
    protected Double price = 0.00;

    @Column(name = "is_published")
    protected Boolean published = false;

    @ManyToMany
    @JoinTable(name = "event_user",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    protected List<User> subscribers;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getMaxTickets() {
        return maxTickets;
    }

    public void setMaxTickets(Integer maxTickets) {
        this.maxTickets = maxTickets;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public final static String getTableName()
    {
        return "Event";
    }
}
