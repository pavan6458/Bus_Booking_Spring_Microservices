package Busscheduleservice.BusSchedule.Service.Client;

import Busscheduleservice.BusSchedule.DTO.Response.BusRegResp;
import Busscheduleservice.BusSchedule.DTO.Response.ScheduleDtoWithBus;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "busadmin",fallback = BusServiceFallback.class)
public interface BusServicesFeign {

    @GetMapping("/api/bus/ms/{msbusId}")
    public ResponseEntity<BusRegResp> getBusByIdms(@PathVariable(name = "msbusId") Integer busId);
}
