package Busscheduleservice.BusSchedule.Service;


import Busscheduleservice.BusSchedule.DTO.Response.BookedSeats;
import Busscheduleservice.BusSchedule.DTO.Response.PassangerDto;
import Busscheduleservice.BusSchedule.DTO.Response.SeatAvailblleDTO;

public interface SeatAvailbleService {
    public SeatAvailblleDTO checkSeatAvailiable(PassangerDto passangerDto);

    public boolean bookSeats(PassangerDto passangerDto);

    public BookedSeats reservedSeats(Integer scheduleId);



}
