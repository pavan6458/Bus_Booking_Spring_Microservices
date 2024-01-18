package BusAdminServices.BusAdmin.Service;

import BusAdminServices.BusAdmin.DTO.Request.BusOperatorDtoReq;
import BusAdminServices.BusAdmin.DTO.Response.BusOperatorDtoResp;

import java.util.List;

public interface BusOperatorService {

    public BusOperatorDtoResp createBusOperator(BusOperatorDtoReq BusOperatorDtoReq);

    public BusOperatorDtoResp UpdateBusOperator(Integer busOperatorId , BusOperatorDtoReq BusOperatorDtoReq);

    public List<BusOperatorDtoResp> getAllBusOperators(Integer id);

    public BusOperatorDtoResp getBusOperatorsById(Integer id);

    public BusOperatorDtoResp DeleteBusOperatorByid(Integer id);
}
