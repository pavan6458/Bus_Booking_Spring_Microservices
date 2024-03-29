package Busscheduleservice.BusSchedule.Controller;


import Busscheduleservice.BusSchedule.DTO.Request.BusSearchReqDto;
import Busscheduleservice.BusSchedule.DTO.Request.ScheduleRegReq;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDTo;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDtoWithBus;
import Busscheduleservice.BusSchedule.Service.ScheduleService;
import Busscheduleservice.BusSchedule.Utils.ResponseGenerater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("api/bus/scheudle")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createSchedule(@RequestBody ScheduleRegReq scheduleRegReq){
        ScheduleDTo schedule = scheduleService.createSchedule(scheduleRegReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED,"Bus Scheduled Created Successsfully",schedule);
    }

    @PostMapping("/SeachBuses")
    public ResponseEntity<Object> searchBuses(@RequestBody BusSearchReqDto busSearchReqDto){
        Set<ScheduleDtoWithBus> scheduleRegResps = scheduleService.searchBus(busSearchReqDto);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Scheduled fetched Successsfully",scheduleRegResps);
    }
    @GetMapping("/All/{adminId}")
    public ResponseEntity<Object> getAllSchedule(@PathVariable(value = "adminId")Integer adminid)
    {
        Set<ScheduleDtoWithBus> allSchedule = scheduleService.getAllSchedule(adminid);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Scheduled fetched Successsfully",allSchedule);

    }
    @GetMapping("/{scheduleId}")
    public  ResponseEntity<Object> getSheduleByID(@PathVariable(value = "scheduleId")Integer scheduleId){
        ScheduleDTo schedule = scheduleService.getSchudleById(scheduleId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Scheduled fetched Successsfully",schedule);
    }
    @DeleteMapping("/{scheduleId}/delete")
    public  ResponseEntity<Object> deleteScheduleById(@PathVariable(value = "scheduleId")Integer integer){
        ScheduleDTo scheduleRegResp = scheduleService.deleteSchudule(integer);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Scheduled deleted Successsfully",scheduleRegResp);
    }

    @PutMapping("/{scheduleId}/update")
    public  ResponseEntity<Object> updateScheduleById(@PathVariable(value = "scheduleId")Integer integer,@RequestBody ScheduleRegReq scheduleRegReq){
        ScheduleDTo scheduleRegResp = scheduleService.updatedSchedule(integer,scheduleRegReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Scheduled updated Successsfully",scheduleRegResp);
    }

}
