package BusAdminServices.BusAdmin.Service;

import BusAdminServices.BusAdmin.DTO.Request.AdminRegReq;
import BusAdminServices.BusAdmin.DTO.Response.AdminRegResp;

public interface BusCompanyAdminService {
    public AdminRegResp createAdmin(AdminRegReq adminRegReq);
    public AdminRegResp LoginAdmin(String email);

}
