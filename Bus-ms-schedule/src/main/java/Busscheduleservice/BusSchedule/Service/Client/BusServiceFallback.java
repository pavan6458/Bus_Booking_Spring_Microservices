package Busscheduleservice.BusSchedule.Service.Client;

import Busscheduleservice.BusSchedule.DTO.Response.BusRegResp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BusServiceFallback implements BusServicesFeign{
    @Override
    public ResponseEntity<BusRegResp> getBusByIdms(Integer busId) {
        return null;
    }
}
