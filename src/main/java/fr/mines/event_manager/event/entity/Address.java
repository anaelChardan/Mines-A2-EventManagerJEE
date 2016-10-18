package fr.mines.event_manager.event.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable @Access(AccessType.FIELD)
public class Address {
    @Column(name = "address1")
    protected String address1;

    @Column(name = "address2")
    protected String address2;

    @Column(name = "zip_code")
    protected String zipCode;

    @Column(name = "city")
    protected String city;

    @Column(name = "country")
    protected String country;
}
