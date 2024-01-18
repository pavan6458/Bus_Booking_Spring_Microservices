package Userservice.User.Controller;


import Userservice.User.DTO.Request.CreateProfileDtoReq;
import Userservice.User.DTO.Request.LoginOtpReq;
import Userservice.User.DTO.Request.MobileNoReq;
import Userservice.User.DTO.Request.ProfileUpdate;
import Userservice.User.DTO.Response.LoginDtoResp;
import Userservice.User.Entity.User;
import Userservice.User.Repository.UserRepository;
import Userservice.User.Service.UserService;
import Userservice.User.Utils.ResponseGenerater;
import Userservice.User.Utils.SecurityConstants;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired

    public UserController(UserService userService, UserRepository userRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/public/SendOtp")
    public ResponseEntity<Object> loginViaOtp(@RequestBody MobileNoReq mobileNoReq) {
        userService.SendOtp(mobileNoReq.getMobile());
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED, "Otp Sent Seccussfully to mobile number " + mobileNoReq.getMobile(), "Please verify the Otp");
    }

    @PostMapping("/public/otpVerify")
    public ResponseEntity<Object> otpVerify(@RequestBody LoginOtpReq loginOtpReq) {
        String isOtpVerified = userService.otpVerifiried(loginOtpReq);

        if (isOtpVerified.toLowerCase().contains("successfully")) {
            // If OTP is verified Successfully, generate JWT token
            User user = userRepository.findByMobileNumber(loginOtpReq.getMobileNo());

            if (user != null) {
                try {
//                    String jwtToken = generateJwtToken(user);
                    Map<String, String> token = new HashMap<>();
//                    token.put("token", jwtToken);


                    return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "OTP verified Successfully with mobile number " + loginOtpReq.getMobileNo(), token);
                } catch (Exception e) {
                    log.error("Error generating JWT token", e);
                    return new ResponseEntity<>(ResponseGenerater.ResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "Error z JWT token", null), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }


        return new ResponseEntity<>(ResponseGenerater.ResponseBuilder(HttpStatus.UNAUTHORIZED, "OTP verification failed or user not found", null), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/private/createProfile")
    public ResponseEntity<Object> createProfile(@RequestBody CreateProfileDtoReq createProfileDtoReq) {
        LoginDtoResp user = userService.createUser(createProfileDtoReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "Profile Created Successfully", user);
    }

    @PostMapping("/private/updateProfile")
    public ResponseEntity<Object> updateProofile(@RequestBody ProfileUpdate createProfileDtoReq) {
        LoginDtoResp user = userService.updateProfile(createProfileDtoReq);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "Profile Created Successfully", user);
    }


//    @PostMapping("/public/login")
//    public ResponseEntity<Object> login(Authentication authentication, HttpServletResponse response) {
//
//        LoginDtoResp user = userService.LoginUser(authentication.getName());
//        User user1 = userRepository.findByMobileNumber(authentication.getName());
//
//        if (user1 != null) {
//            try {
//                String jwtToken = generateJwtToken(user1);
//                user.setToken(jwtToken);
//                return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "Login in Successfull ", user);
//
//
//            } catch (Exception e) {
//                log.error("Error generating JWT token", e);
//                return new ResponseEntity<>(ResponseGenerater.ResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating JWT token", user), HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        }
//        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "Login Successfully", user);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") Integer id) {
        LoginDtoResp userById = userService.getUserById(id);


        return ResponseGenerater.ResponseBuilder(HttpStatus.OK, "user Fetched Successfully", userById);
    }


//    private String generateJwtToken(User user) {
//        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_TokenGenerationKEy.getBytes(StandardCharsets.UTF_8));
//        return Jwts.builder().setIssuer("pavan").setSubject("jwtToken")
//               .claim("username", user.getName())
//                .claim("id", user.getId()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME)).signWith(key).compact();
//    }


}
