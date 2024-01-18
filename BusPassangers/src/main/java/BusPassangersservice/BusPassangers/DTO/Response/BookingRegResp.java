package BusPassangersservice.BusPassangers.DTO.Response;


import lombok.Data;

@Data
public class BookingRegResp {
    private Integer id;
    private Integer totalPassengers;
    private Double totalAmount;
    private String status;




}
