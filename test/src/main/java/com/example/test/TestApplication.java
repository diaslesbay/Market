package com.example.test;

import com.example.test.enums.TypeOfUser;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findUsersByStatus(TypeOfUser.ADMIN);
		if(adminAccount == null){
			User user = new User();
			user.setUsername("admin");
			user.setStatus(TypeOfUser.ADMIN);
			user.setEmail("admin@gmail.com");
			user.setLastname("admin");
			user.setFirstname("admin");
			user.setAddress(null);
			user.setPhoneNumber("+7737-233-21-21");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
//{
//		"username":"dias_ledewsbay13",
//		"password":"ASD123dsa",
//		"firstname":"FN",
//		"lastname":"LN",
//		"phoneNumber":"+7721-614-90-22",
//		"address":"ALA",
//		"email":"223@ldwqd.cpm"
//		}

//{
//		"username":"dias_ledwsbay13",
//		"password":"ASD123dsa",
//		"firstname":"FN",
//		"lastname":"LN",
//		"phoneNumber":"+7711-614-90-22",
//		"address":"ALA",
//		"email":"2231@ldwqd.cpm"
//		}
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaWFzX2xlZHdzYmF5MTMiLCJyb2xlcyI6WyJSRUdJU1RFUkVEX1VTRVIiXSwiZXhwIjoxNjk5ODg0MTMxLCJpYXQiOjE2OTk4ODM5NTF9.PQFVFu6nA3Q8_-OITlyc0CRVb6aQJ6UDmW1jXdrtOMc