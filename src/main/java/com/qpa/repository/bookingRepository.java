package com.qpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qpa.entities.slotBookingInfo;
import com.qpa.entities.slotInfo;
import com.qpa.entities.vehicleInfo;

@Repository
public interface bookingRepository extends JpaRepository<slotBookingInfo, Integer> {

	 boolean existsBySlotObj_SlotIdAndVehicleObj_VehicleId( int slotId, int vehicleId);
	 List<slotBookingInfo> findByVehicleObj_VehicleId(int vehicleId);
}
