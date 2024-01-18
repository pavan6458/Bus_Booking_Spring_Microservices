package Busscheduleservice.BusSchedule.DTO.Response;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class BookedSeats {
    Set<Integer> seatReserved = new HashSet<>();
}
