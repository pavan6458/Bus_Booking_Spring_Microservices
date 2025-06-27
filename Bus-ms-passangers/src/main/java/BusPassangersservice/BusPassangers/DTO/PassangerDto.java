package BusPassangersservice.BusPassangers.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PassangerDto {
    private Integer bookingId;
    private Integer scheduleId;
    private List<PassangerList> passangerLists;
}
