package BusAdminServices.BusAdmin.Controller;

import BusAdminServices.BusAdmin.DTO.Request.AdminRegReq;
import BusAdminServices.BusAdmin.DTO.Response.AdminRegResp;
import BusAdminServices.BusAdmin.Service.BusCompanyAdminService;
import BusAdminServices.BusAdmin.Utils.ResponseGenerater;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Validated
@Slf4j
public class BusAdminController {

    private final BusCompanyAdminService busCompanyAdminService;

    public BusAdminController(BusCompanyAdminService busCompanyAdminService) {
        this.busCompanyAdminService = busCompanyAdminService;
    }
    @Value("${build.version}")
    private String version;



    @PostMapping("/public/create")
    public ResponseEntity<Object> createUser(@RequestBody @Valid AdminRegReq adminRegReq) {
        AdminRegResp admin = busCompanyAdminService.createAdmin(adminRegReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED, "Bus Admin Created successfully", admin);
    }

        @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        log.info(version);
        return new ResponseEntity<>(version, HttpStatus.OK);

    }

//    @PostMapping("/public/login")
//    public ResponseEntity<Object> createUser (Authentication authentication){
//        AdminRegResp admin = busCompanyAdminService.LoginAdmin(authentication.getName());
//        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Login successfull",admin);
//    }
}
