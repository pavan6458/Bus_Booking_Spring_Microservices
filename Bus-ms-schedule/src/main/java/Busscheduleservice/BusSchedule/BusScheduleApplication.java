package Busscheduleservice.BusSchedule;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BusScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusScheduleApplication.class, args);
	}
	@Bean
	public ModelMapper modalmapper(){
		return new ModelMapper();
	}
}
