package BusPassangersservice.BusPassangers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusPassangersApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusPassangersApplication.class, args);
	}

	@Bean
	public ModelMapper modalmapper(){
		return new ModelMapper();
	}

}
