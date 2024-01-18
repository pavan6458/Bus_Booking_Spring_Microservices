package BusAdminServices.BusAdmin.DTO.Response;

import lombok.Data;

import java.util.Date;
@Data
public class BusOperatorDtoResp {
    private Integer id;
    private String operatorName;
    private String operatorEmail;
    private String OperatorMobile;
    private Date createdDate;
    private Date UpdatedDate;

}
