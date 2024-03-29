package BusPassangersservice.BusPassangers.Service.Client;

import BusPassangersservice.BusPassangers.DTO.PassangerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("busschedule")
public interface seatAvailabilityFeign {
    @PostMapping("/api/bus/mscheckSeat")
    public ResponseEntity<Boolean> mscheckavailiability(@RequestBody PassangerDto passangerDto);
}
