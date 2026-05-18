package prm.projectbase;

import prm.projectbase.entity.Role;
import prm.projectbase.entity.User;
import prm.projectbase.repository.RoleRepository;
import prm.projectbase.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner databaseInitializer(
			RoleRepository roleRepository,
			UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			if (roleRepository.count() == 0) {
				// 1. Initialize Roles
				Role adminRole = roleRepository.save(Role.builder()
						.name("ROLE_ADMIN")
						.description("Administrator with full system privileges")
						.build());

				Role userRole = roleRepository.save(Role.builder()
						.name("ROLE_USER")
						.description("Standard User with restricted read access")
						.build());

				// 2. Initialize Users (Hashed passwords)
				userRepository.save(User.builder()
						.userName("admin")
						.password(passwordEncoder.encode("admin123"))
						.email("admin@prm.com")
						.fullName("System Administrator")
						.active(true)
						.role(adminRole)
						.build());

				userRepository.save(User.builder()
						.userName("user")
						.password(passwordEncoder.encode("user123"))
						.email("user@prm.com")
						.fullName("Standard User")
						.active(true)
						.role(userRole)
						.build());

				System.out.println("\n======================================================================");
				System.out.println("   DATABASE SEEDED SUCCESSFULLY FOR DEVELOPMENT & LOCAL TESTING      ");
				System.out.println("======================================================================");
				System.out.println("Admin Account details       -> Username: admin, Password: admin123");
				System.out.println("Standard Account details    -> Username: user,  Password: user123");
				System.out.println("H2 Database Console URL     -> http://localhost:5001/h2-console");
				System.out.println("======================================================================\n");
			}
		};
	}
}
