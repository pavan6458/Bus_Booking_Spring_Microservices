package Userservice.User.Service;


import Userservice.User.DTO.Request.CreateProfileDtoReq;
import Userservice.User.DTO.Request.LoginOtpReq;
import Userservice.User.DTO.Request.ProfileUpdate;
import Userservice.User.DTO.Response.LoginDtoResp;
import Userservice.User.Entity.User;

public interface UserService {
    public String SendOtp(String mobileNo);
    public String otpVerifiried(LoginOtpReq loginOtpReq);
    public LoginDtoResp createUser(CreateProfileDtoReq createProfileDtoReq);
    public LoginDtoResp LoginUser(String mobileNo);

    public LoginDtoResp getUserById(Integer id);

    public User findUserByUsername(String username);

    public LoginDtoResp updateProfile(ProfileUpdate profileUpdate);






}
