package BusPassangersservice.BusPassangers.Service.Client;

import BusPassangersservice.BusPassangers.DTO.PassangerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SeatFallBack implements seatAvailabilityFeign{
    @Override
    public ResponseEntity<Boolean> mscheckavailiability(PassangerDto passangerDto) {
        return null;
    }
}
