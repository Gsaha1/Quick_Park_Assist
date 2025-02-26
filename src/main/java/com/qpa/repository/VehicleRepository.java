package com.qpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qpa.entities.*;

@Repository
public interface VehicleRepository extends JpaRepository<vehicleInfo, Integer> {
}
