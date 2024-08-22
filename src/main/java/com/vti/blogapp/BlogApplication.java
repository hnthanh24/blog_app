package com.vti.blogapp;

import com.vti.blogapp.entity.Role;
import com.vti.blogapp.entity.User;
import com.vti.blogapp.repository.RoleRepository;
import com.vti.blogapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@AllArgsConstructor
@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var user = new User();
		user.setName("Nguyễn Duy Hưng");
		user.setUsername("hung.nd");
		user.setEmail("hung.nd@gmail.com");
		user.setPassword(passwordEncoder.encode("123456Aa@"));
		var adminRole = roleRepository.findByType(Role.Type.ADMIN);
		var userRole = roleRepository.findByType(Role.Type.USER);
		user.setRoles(Set.of(adminRole,userRole));
		userRepository.save(user);
	}
}
