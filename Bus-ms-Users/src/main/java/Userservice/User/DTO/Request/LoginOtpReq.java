package Userservice.User.DTO.Request;

import lombok.Data;

@Data
public class LoginOtpReq {
    private String mobileNo;
    private String otp;
}
