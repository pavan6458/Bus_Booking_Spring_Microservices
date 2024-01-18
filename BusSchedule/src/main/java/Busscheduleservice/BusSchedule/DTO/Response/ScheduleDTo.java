package Busscheduleservice.BusSchedule.DTO.Response;


import Busscheduleservice.BusSchedule.DTO.Response.BusRegResp;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDTo {
    private Integer id;
    private Date arrivalTime;
    private Date departureTime;
    private Double price;
    private String duration;
    private String origin;
    private String destination;
    private BusRegResp bus;
}
