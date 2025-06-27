package BusPassangersservice.BusPassangers.Repository;


import BusPassangersservice.BusPassangers.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
    List<Passenger> findByScheduleId(Integer scheduleId);
    List<Passenger> findByBookingId(Integer bookingId);
}
