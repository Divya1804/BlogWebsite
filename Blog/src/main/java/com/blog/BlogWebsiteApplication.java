package com.blog;

import com.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.blog.entities.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@SpringBootApplication
public class BlogWebsiteApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogWebsiteApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role = new Role();
			role.setId(1);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(2);
			role1.setName("NORMAL_USER");

			List<Role> roles = List.of(role, role1);
			List<Role> finalRoles = roleRepo.saveAll(roles);
			finalRoles.forEach(r -> {
				System.out.println(r.getName());
			});
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
