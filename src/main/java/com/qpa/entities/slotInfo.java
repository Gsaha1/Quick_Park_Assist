package com.qpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "slot_info")
public class slotInfo {

    @Id
    private int slotId;
    private String slotStatus;

    

	
	public String getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}

	public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
