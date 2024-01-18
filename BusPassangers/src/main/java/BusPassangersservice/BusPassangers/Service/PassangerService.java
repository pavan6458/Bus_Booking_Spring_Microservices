package BusPassangersservice.BusPassangers.Service;


import BusPassangersservice.BusPassangers.DTO.PassangerDto;
import BusPassangersservice.BusPassangers.DTO.PassangerList;

import java.util.List;

public interface PassangerService {
    public PassangerDto addPassangersToBooking (PassangerDto passangerDto);

    public List<PassangerList> GetAllSchedulePassangersList(Integer scheduleId);

    public  List<PassangerList> getPassangersByBookingId(Integer bookingId);



}
