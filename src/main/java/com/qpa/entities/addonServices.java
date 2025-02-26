package com.qpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "addon_services")
public class addonServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String serviceType;
    private double price;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnore 
    private slotBookingInfo booking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public slotBookingInfo getBooking() {
        return booking;
    }

    public void setBooking(slotBookingInfo booking) {
        this.booking = booking;
    }
}
