package Busscheduleservice.BusSchedule.DTO.Response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class SeatAvailblleDTO {
    private Map<Integer,String> seatMessage = new HashMap<>();
}
