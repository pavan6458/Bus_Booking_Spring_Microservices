package BusPassangersservice.BusPassangers.Repository;


import BusPassangersservice.BusPassangers.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    public List<Booking> findByUserId(Integer id);

}

