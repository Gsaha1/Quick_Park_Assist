package com.qpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qpa.EntityException.EntityNotFoundException;
import com.qpa.entities.addonServices;
import com.qpa.entities.slotBookingInfo;
import com.qpa.entities.slotInfo;
import com.qpa.entities.vehicleInfo;
import com.qpa.repository.SlotRepository;
import com.qpa.repository.VehicleRepository;
import com.qpa.repository.bookingRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class bookingServices {

	
	@Autowired
	bookingRepository bookingRepository;
	
	
	
	
	  @PersistenceContext
	    private EntityManager entityManager;  // ✅ Use EntityManager to manage entities

	  
	  
	  //Add bookings
	    @Transactional
	    public ResponseEntity<?> addBooking(int slotId, int vehicleId, slotBookingInfo bookingInfo) {
	        if (bookingRepository.existsBySlotObj_SlotIdAndVehicleObj_VehicleId(slotId, vehicleId)) {
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("Slot ID " + slotId + " and Vehicle ID " + vehicleId + " are already assigned to another booking.");
	        }
	        slotInfo slot = entityManager.find(slotInfo.class, slotId);
	        if (slot == null) {
	            slot = new slotInfo();
	            slot.setSlotId(slotId); // Set new slot ID
	            slot.setSlotStatus("Available"); // Default status
	            entityManager.persist(slot); // Save to DB
	        }

	        vehicleInfo vehicle = entityManager.find(vehicleInfo.class, vehicleId);
	        if (vehicle == null) {
	            vehicle = new vehicleInfo();
	            vehicle.setVehicleId(vehicleId); // Set new vehicle ID
	            vehicle.setVehicleType("Unknown"); // Default type
	            entityManager.persist(vehicle); // Save to DB
	        }

	        bookingInfo.setSlotObj(slot);
	        bookingInfo.setVehicleObj(vehicle);

	        if (bookingInfo.getAddOnServices() != null) {
	            bookingInfo.getAddOnServices().forEach(service -> service.setBooking(bookingInfo));
	        }
	        slotBookingInfo savedBooking = bookingRepository.save(bookingInfo);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
	    }

	
	//get all bookings
	public List<slotBookingInfo> findAllBookingInfos(){
		return bookingRepository.findAll();
	}
	
	//get booking by id
	public slotBookingInfo findBookingById(int bookingId) {
		Optional<slotBookingInfo> optional= bookingRepository.findById(bookingId);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new EntityNotFoundException("Booking does not exists with id:"+bookingId);
	}
	
	//get booking by vehicle id
	public List<slotBookingInfo> getBookingsByVehicleId(int vehicleId) {
        List<slotBookingInfo> bookings = bookingRepository.findByVehicleObj_VehicleId(vehicleId);
        
        // Throw custom exception if no bookings found
        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("No bookings found for vehicle ID: " + vehicleId);
        }
        return bookings;
    }

	
	
	
	
	
}
