package Busscheduleservice.BusSchedule.DTO.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRegResp {
    private Integer id;
    private String busName;
    private Integer totalSeats;
    private String busType;
    private String seatType;
    private Integer busOperatorId;
}
