package com.qpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_info")
public class vehicleInfo {

    @Id
    private int vehicleId;
    private String vehicleType;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
