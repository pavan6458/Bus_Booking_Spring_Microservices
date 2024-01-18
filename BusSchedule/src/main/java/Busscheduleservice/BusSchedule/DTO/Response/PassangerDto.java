package Busscheduleservice.BusSchedule.DTO.Response;

import lombok.Data;

import java.util.List;
@Data
public class PassangerDto {
    private Integer bookingId;
    private Integer scheduleId;
    private List<PassangerList> passangerLists;
}
