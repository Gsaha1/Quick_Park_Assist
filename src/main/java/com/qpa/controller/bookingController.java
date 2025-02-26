package com.qpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qpa.entities.slotBookingInfo;
import com.qpa.services.bookingServices;

@RestController

public class bookingController {

	
	@Autowired
	bookingServices bookingServices;
	

	 @PostMapping("/add/{slotId}/{vehicleId}")
	    public ResponseEntity<?> addBooking(
	            @PathVariable int slotId,
	            @PathVariable int vehicleId,
	            @RequestBody slotBookingInfo bookingInfo) {
	        return bookingServices.addBooking(slotId, vehicleId, bookingInfo);
	    }
	    
	    @GetMapping("/viewAllBookings")
	    public ResponseEntity<List<slotBookingInfo>> viewAllBookingInfos(){
	    	List<slotBookingInfo> bookings=bookingServices.findAllBookingInfos();
	    	return ResponseEntity.ok(bookings);
	    }
	    
	    @GetMapping("/viewBookingById/{bookingId}")
	    public ResponseEntity<slotBookingInfo> viewBookingById(@PathVariable int bookingId) {
	    	slotBookingInfo bookingInfo=bookingServices.findBookingById(bookingId);
	    	return ResponseEntity.ok(bookingInfo);
	    	
	    }
	    
	    @GetMapping("/viewBookingByVehicleId/{vehicleId}")
	    public List<slotBookingInfo> viewBookingByVehicleId(@PathVariable int vehicleId) {
	        return bookingServices.getBookingsByVehicleId(vehicleId);
	    }
	    

}
