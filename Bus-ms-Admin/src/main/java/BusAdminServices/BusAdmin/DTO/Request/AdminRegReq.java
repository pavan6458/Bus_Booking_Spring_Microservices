package BusAdminServices.BusAdmin.DTO.Request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AdminRegReq {
    @NotEmpty(message = "companyName cannot be null or empty")
    private String companyName;
    @NotEmpty(message = "companyEmail cannot be null or empty")
    @Email
    private String companyEmail;
    @NotEmpty(message = "CompanyPhone cannot be null or empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Please enter a 10-digit number")
    private String companyPhone;

    @NotEmpty(message = "passwordHash cannot be null or empty")
    private String passwordHash;
}
