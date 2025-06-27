package Userservice.User.DTO.Request;

import lombok.Data;

@Data
public class ProfileUpdate {
    private Integer id;

    private String name;

    private String email;

}
