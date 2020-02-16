package ua.com.alevel.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityApplicationTests {

	@Test
	void contextLoads() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = "admin";
		System.out.println("encoder = " + encoder.encode(pass));
		System.out.println("encoder = " + encoder.encode(pass));
		System.out.println("encoder = " + encoder.encode(pass));

		String passEncoder = encoder.encode(pass);

		System.out.println("encoder = " + encoder.matches(pass, passEncoder));
	}

}
