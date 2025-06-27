package Userservice.User.DTO.Request;

import lombok.Data;

@Data
public class LoginDtoReq {
    private String  mobileNo;
    private String password;

}
