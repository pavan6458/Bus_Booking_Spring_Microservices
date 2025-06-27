package Busscheduleservice.BusSchedule.Service;


import Busscheduleservice.BusSchedule.DTO.Request.BusSearchReqDto;
import Busscheduleservice.BusSchedule.DTO.Request.ScheduleRegReq;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDTo;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDtoWithBus;

import java.util.Set;

public interface ScheduleService {
    public ScheduleDTo createSchedule(ScheduleRegReq scheduleRegReq);
    public ScheduleDTo updatedSchedule(Integer sheduleId , ScheduleRegReq scheduleRegReq);
    public Set<ScheduleDtoWithBus> getAllSchedule(Integer adminId);
    public ScheduleDTo getSchudleById(Integer sheduleId);
    public ScheduleDTo deleteSchudule(Integer sheduleId);
    
    public Set<ScheduleDtoWithBus> searchBus(BusSearchReqDto busSearchReqDto);

}
