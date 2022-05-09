package nirmalya.aathithya.webmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.user.model.UserRolesAndModuleIdModel;

@SpringBootApplication 
public class WebModuleApplication {
	// latest code comment
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public EnvironmentVaribles environmentVaribles() {
		EnvironmentVaribles env = new EnvironmentVaribles();
		return env;
	}

	

	@Bean
	public UserRolesAndModuleIdModel userModel() {
		return new UserRolesAndModuleIdModel();
	}
	public static void main(String[] args) {
		SpringApplication.run(WebModuleApplication.class, args);
	}

}
