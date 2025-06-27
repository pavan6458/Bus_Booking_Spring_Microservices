package BusPassangersservice.BusPassangers.Controller;


import BusPassangersservice.BusPassangers.DTO.PassangerDto;
import BusPassangersservice.BusPassangers.DTO.PassangerList;
import BusPassangersservice.BusPassangers.Service.PassangerService;
import BusPassangersservice.BusPassangers.Utils.ResponseGenerater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus/passangers")
public class PassangersController {
    private PassangerService passangerService;

    public PassangersController(PassangerService passangerService) {
        this.passangerService = passangerService;
    }

    @PostMapping("/addpassangers")
    public ResponseEntity<Object> addPassangers(@RequestBody PassangerDto passangerDto) {
        PassangerDto passangerDto1 = passangerService.addPassangersToBooking(passangerDto);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED, "Passangers add successfully", passangerDto1);
    }

    @GetMapping("/ListOfScheduledPassangers/{scheduleId}")
    public ResponseEntity<Object> ListOfScheduledPassangers(@PathVariable (name = "scheduleId")Integer scheduleId)
    {
        List<PassangerList> passangerLists = passangerService.GetAllSchedulePassangersList(scheduleId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "List of Scheduled Passangers", passangerLists);

    }

    @GetMapping("/getByBookingId/{bookingid}")
    public ResponseEntity<Object> getByBookingId(@PathVariable (name = "bookingid")Integer scheduleId)
    {
        List<PassangerList> passangerLists = passangerService.getPassangersByBookingId(scheduleId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "passangers fetched seccessfully", passangerLists);

    }
}
