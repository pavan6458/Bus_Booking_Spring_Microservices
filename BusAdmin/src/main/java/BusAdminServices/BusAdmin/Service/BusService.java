package BusAdminServices.BusAdmin.Service;

import BusAdminServices.BusAdmin.DTO.Request.BusRegReq;
import BusAdminServices.BusAdmin.DTO.Response.BusRegResp;
import java.util.List;

public interface BusService {
    public BusRegResp createBus(BusRegReq busRegReq);

    public BusRegResp UpdateBus(Integer BusId , BusRegReq busRegReq);

    public List<BusRegResp> getAllBus(Integer id);

    public BusRegResp getBusById(Integer id);

    public BusRegResp DeleteBusByid(Integer id);

}
