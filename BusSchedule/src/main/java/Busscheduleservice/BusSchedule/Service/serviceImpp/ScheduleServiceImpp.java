package Busscheduleservice.BusSchedule.Service.serviceImpp;


import Busscheduleservice.BusSchedule.DTO.Request.BusSearchReqDto;
import Busscheduleservice.BusSchedule.DTO.Request.ScheduleRegReq;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDTo;
import Busscheduleservice.BusSchedule.Entity.Schedule;
import Busscheduleservice.BusSchedule.Exception.DataNotFounException;
import Busscheduleservice.BusSchedule.Repository.ScheduleRepository;
import Busscheduleservice.BusSchedule.Service.ScheduleService;
import Busscheduleservice.BusSchedule.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpp implements ScheduleService {
    private ModelMapper mapper;
    private ScheduleRepository scheduleRepository;


    public ScheduleServiceImpp(ModelMapper mapper, ScheduleRepository scheduleRepository) {
        this.mapper = mapper;
        this.scheduleRepository = scheduleRepository;

    }

    @Override
    public ScheduleDTo createSchedule(ScheduleRegReq scheduleRegReq) {
        Schedule schedule = new Schedule();
        schedule.setId(GenerateId.BuildId());
        schedule.setDepartureTime(scheduleRegReq.getDepartureTime());
        schedule.setArrivalTime(scheduleRegReq.getArrivalTime());
        schedule.setPrice(scheduleRegReq.getPrice());
        schedule.setDuration(scheduleRegReq.getDuration());
        schedule.setOrigin(scheduleRegReq.getOrigin());
        schedule.setDestination(scheduleRegReq.getDestination());
        schedule.setDistance(scheduleRegReq.getDistance());
        schedule.setBusId(scheduleRegReq.getBusId());
        schedule.setBusCompanyAdminId(scheduleRegReq.getBusId());
        Schedule Scheduled = scheduleRepository.save(schedule);

        return convertScheduleToScheduleRegResp.apply(Scheduled);
    }

    @Override
    public ScheduleDTo updatedSchedule(Integer scheduleId, ScheduleRegReq scheduleRegReq) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new DataNotFounException("Schedule not  found with id " + scheduleId));
        schedule.setDepartureTime(scheduleRegReq.getDepartureTime());
        schedule.setArrivalTime(scheduleRegReq.getArrivalTime());
        schedule.setPrice(scheduleRegReq.getPrice());
        schedule.setDuration(scheduleRegReq.getDuration());
        schedule.setOrigin(scheduleRegReq.getDuration());
        schedule.setDestination(scheduleRegReq.getDestination());
        schedule.setDistance(scheduleRegReq.getDistance());
        Schedule save = scheduleRepository.save(schedule);
        return convertScheduleToScheduleRegResp.apply(save);
    }

    @Override
    public Set<ScheduleDTo> getAllSchedule(Integer adminId) {
        List<Schedule> scheduleList = scheduleRepository.findByBusCompanyAdminId(adminId);

        return scheduleList.stream().map(convertScheduleToScheduleRegResp).collect(Collectors.toSet());
    }



    @Override
    public ScheduleDTo getSchudleById(Integer sheduleId) {
        Schedule schedule = scheduleRepository.findById(sheduleId)
                .orElseThrow(() -> new DataNotFounException("Schedule not found with id" + sheduleId));
        return convertScheduleToScheduleRegResp.apply(schedule);
    }

    @Override
    public ScheduleDTo deleteSchudule(Integer sheduleId) {
        Schedule schedule = scheduleRepository.findById(sheduleId)
                .orElseThrow(() -> new DataNotFounException("Schedule not found with id" + sheduleId));
        scheduleRepository.delete(schedule);
        Optional<Schedule> deletedUser = scheduleRepository.findById(sheduleId);
        if(deletedUser.isEmpty())
        {
            return convertScheduleToScheduleRegResp.apply(schedule);
        }
        return null;
    }

    @Override
    public Set<ScheduleDTo> searchBus(BusSearchReqDto busSearchReqDto) {
        List<Schedule> searchedBuses = scheduleRepository.findByOriginAndDestinationAndArrivalDate(busSearchReqDto.getOrigin(), busSearchReqDto.getDestination(), busSearchReqDto.getArrivalTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Set<ScheduleDTo> collect = searchedBuses.stream().map(convertScheduleToScheduleRegResp).collect(Collectors.toSet());
        return collect;
    }

    Function<Schedule,ScheduleDTo> convertScheduleToScheduleRegResp = s1 -> mapper.map(s1,ScheduleDTo.class);



}
