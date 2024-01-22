package BusAdminServices.BusAdmin;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableRetry
public class BusAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusAdminApplication.class, args);
	}
	@Bean
	public ModelMapper modalmapper(){
		return new ModelMapper();
	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
