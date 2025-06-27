package BusAdminServices.BusAdmin.DTO.Request;

import lombok.Data;

@Data
public class BusRegReq {
    private String busName;
    private Integer totalSeats;
    private String busType;
    private Integer busOperator;
    private Integer adminID;
        private String seatType;
}
