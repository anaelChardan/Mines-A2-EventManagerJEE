package fr.mines.event_manager.event.entity;

import fr.mines.event_manager.framework.entity.AbstractEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable @Access(AccessType.FIELD)
public class Address extends AbstractEntity{
    @Column(name = "address1", nullable = false)
    protected String address1;

    @Column(name = "address2")
    protected String address2;

    @Column(name = "zip_code", nullable = false)
    protected String zipCode;

    @Column(name = "city", nullable = false)
    protected String city;

    @Column(name = "country", nullable = false)
    protected String country;

    public String getAddress1() {
        return address1;
    }

    public Address setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public Address setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }
}
