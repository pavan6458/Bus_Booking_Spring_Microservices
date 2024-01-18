package BusAdminServices.BusAdmin.Controller;

import BusAdminServices.BusAdmin.DTO.Request.AdminRegReq;
import BusAdminServices.BusAdmin.DTO.Response.AdminRegResp;
import BusAdminServices.BusAdmin.Service.BusCompanyAdminService;
import BusAdminServices.BusAdmin.Utils.ResponseGenerater;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@Validated
public class BusAdminController {
    private BusCompanyAdminService busCompanyAdminService;

    public BusAdminController(BusCompanyAdminService busCompanyAdminService) {
        this.busCompanyAdminService = busCompanyAdminService;
    }
    @PostMapping("/public/create")
    public ResponseEntity<Object> createUser (@RequestBody @Valid AdminRegReq adminRegReq){
        AdminRegResp admin = busCompanyAdminService.createAdmin(adminRegReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED,"Bus Admin Created successfully",admin);
    }

//    @PostMapping("/public/login")
//    public ResponseEntity<Object> createUser (Authentication authentication){
//        AdminRegResp admin = busCompanyAdminService.LoginAdmin(authentication.getName());
//        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Login successfull",admin);
//    }
}
