package Busscheduleservice.BusSchedule.Service.serviceImpp;


import Busscheduleservice.BusSchedule.DTO.Request.BusSearchReqDto;
import Busscheduleservice.BusSchedule.DTO.Request.ScheduleRegReq;
import Busscheduleservice.BusSchedule.DTO.Response.BusRegResp;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDTo;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDtoWithBus;
import Busscheduleservice.BusSchedule.Entity.Schedule;
import Busscheduleservice.BusSchedule.Exception.DataNotFounException;
import Busscheduleservice.BusSchedule.Repository.ScheduleRepository;
import Busscheduleservice.BusSchedule.Service.Client.BusServicesFeign;
import Busscheduleservice.BusSchedule.Service.ScheduleService;
import Busscheduleservice.BusSchedule.Utils.GenerateId;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScheduleServiceImpp implements ScheduleService {

    private ModelMapper mapper;
    private ScheduleRepository scheduleRepository;
    private BusServicesFeign busServicesFeign;

    public ScheduleServiceImpp(ModelMapper mapper, ScheduleRepository scheduleRepository, BusServicesFeign busServicesFeign) {
        this.mapper = mapper;
        this.scheduleRepository = scheduleRepository;
        this.busServicesFeign = busServicesFeign;
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
        schedule.setBusCompanyAdminId(scheduleRegReq.getAdminId());
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
    public Set<ScheduleDtoWithBus> getAllSchedule(Integer adminId) {
        List<Schedule> scheduleList = scheduleRepository.findByBusCompanyAdminId(adminId);


        return scheduleList.stream().map(convertScheduleToScheduleWithBusRegResp).collect(Collectors.toSet());
    }

    @Override
    public Set<ScheduleDtoWithBus> searchBus(BusSearchReqDto busSearchReqDto) {
        List<Schedule> searchedBuses = scheduleRepository.findByOriginAndDestinationAndArrivalDate(busSearchReqDto.getOrigin(), busSearchReqDto.getDestination(), busSearchReqDto.getArrivalTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return searchedBuses.stream().map(convertScheduleToScheduleWithBusRegResp).collect(Collectors.toSet());

    }



    @Override
    public ScheduleDTo getSchudleById(Integer sheduleId) {
        Schedule schedule = scheduleRepository.findById(sheduleId)
                .orElseThrow(() -> new DataNotFounException("Schedule not found with id" + sheduleId));
        ScheduleDtoWithBus scedule = convertScheduleToScheduleWithBusRegResp.apply(schedule);

                return null;
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



    Function<Schedule,ScheduleDTo> convertScheduleToScheduleRegResp = s1 ->mapper.map(s1, ScheduleDTo.class);


    Function<Schedule, ScheduleDtoWithBus> convertScheduleToScheduleWithBusRegResp = s1 ->{
        BusRegResp bus = busServicesFeign.getBusByIdms(s1.getBusId());
        ScheduleDtoWithBus sachedule = new ScheduleDtoWithBus();
        sachedule.setArrivalTime(s1.getArrivalTime());
        sachedule.setId(s1.getId());
        sachedule.setDuration(s1.getDuration());
        sachedule.setOrigin(s1.getOrigin());
        sachedule.setPrice(s1.getPrice());
        sachedule.setDepartureTime(s1.getDepartureTime());
        sachedule.setDestination(s1.getDestination());
        sachedule.setBus(bus);
        return sachedule;
    };





}
