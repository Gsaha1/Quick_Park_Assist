package com.qpa.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "slot_booking_info")
public class slotBookingInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  // ✅ Fix: Converts String to Date
    @Temporal(TemporalType.DATE)  
    private LocalDate startDate;
    private LocalDate endTime;
    private String paymentMode;
    private boolean bookingStatus;
   
    private double paymentAmount;
    private boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "slot_id") // Foreign key reference
    private slotInfo slotObj;

    @ManyToOne
    @JoinColumn(name = "vehicle_id") // Foreign key reference
    private vehicleInfo vehicleObj;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<addonServices> addOnServices;

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

  

    
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

   
    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public slotInfo getSlotObj() {
        return slotObj;
    }

    public void setSlotObj(slotInfo slotObj) {
        this.slotObj = slotObj;
    }

    public vehicleInfo getVehicleObj() {
        return vehicleObj;
    }

    public void setVehicleObj(vehicleInfo vehicleObj) {
        this.vehicleObj = vehicleObj;
    }

    public List<addonServices> getAddOnServices() {
        return addOnServices;
    }

    public void setAddOnServices(List<addonServices> addOnServices) {
        for (addonServices service : addOnServices) {
            service.setBooking(this); // ✅ Ensuring services are linked before saving
        }
        this.addOnServices = addOnServices;
    }

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
}
