package BusAdminServices.BusAdmin.Controller;

import BusAdminServices.BusAdmin.DTO.Request.BusRegReq;
import BusAdminServices.BusAdmin.DTO.Response.BusRegResp;
import BusAdminServices.BusAdmin.Service.BusService;
import BusAdminServices.BusAdmin.Utils.ResponseGenerater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/bus")
public class BusController {
    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBus(@RequestBody BusRegReq busRegReq) {
        BusRegResp bus = busService.createBus(busRegReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED, "Bus created Successfully", bus);
    }

    @GetMapping("/All/{adminId}")
    public ResponseEntity<Object> getAllBuses(@PathVariable(name = "adminId") Integer adminId)
    {
        List<BusRegResp> allBus = busService.getAllBus(adminId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "fetched all Buses", allBus);
    }
    @GetMapping("/{busId}")
    public ResponseEntity<Object> getBusById(@PathVariable(name = "busId") Integer busId) {
        BusRegResp bus = busService.getBusById(busId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "fetched successfully", bus);
    }

    @GetMapping("/ms/{msbusId}")
    public ResponseEntity getBusByIdms(@PathVariable(name = "msbusId") Integer busId) {
        BusRegResp bus = busService.getBusById(busId);
        return ResponseEntity.ok().body(bus);
    }

    @PutMapping("/{busId}/update")
    public ResponseEntity<Object> updateBusById(@PathVariable(name = "busId") Integer busId, @RequestBody BusRegReq busRegReq) {
        BusRegResp busRegResp = busService.UpdateBus(busId, busRegReq);
        // You might want to return a ResponseEntity with appropriate status and body
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus  Updated Successfully",busRegResp);
    }

    @DeleteMapping("{busId}/delete")
    public ResponseEntity<Object> deleteBusById(@PathVariable(name = "busId")  Integer id)
    {
        BusRegResp busRegResp = busService.DeleteBusByid(id);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Bus Operator Deleted Successfully",busRegResp);

    }
}
