package com.js.escuelas;

import com.js.escuelas.consistence.entity.PermissionEntity;
import com.js.escuelas.consistence.entity.RoleEnum;
import com.js.escuelas.consistence.entity.RolesEntity;
import com.js.escuelas.consistence.entity.UserEntity;
import com.js.escuelas.consistence.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EscuelasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscuelasApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			if (!userRepository.existsByUsername("user") && !userRepository.existsByUsername("admin")) {

				// CREATE PERMISSIONS
				PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build();
				PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();
				PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();
				PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();
				PermissionEntity refactorPermission = PermissionEntity.builder().name("REFACTOR").build();

				// CREATE ROLES
				RolesEntity roleAdmin = RolesEntity.builder()
						.roleEnum(RoleEnum.ADMIN)
						.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
						.build();

				RolesEntity roleUser = RolesEntity.builder()
						.roleEnum(RoleEnum.USER)
						.permissionList(Set.of(readPermission))
						.build();

				// CREATE USERS
				UserEntity user = UserEntity.builder()
						.username("user")
						.password("$2a$10$MoFK19wfqPxJcohprWt.G.Q4Czpx9qyHCfxUCubEksDT4slMsh0dK")
						.isEnabled(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleUser))
						.build();

				UserEntity admin = UserEntity.builder()
						.username("admin")
						.password("$2a$10$MoFK19wfqPxJcohprWt.G.Q4Czpx9qyHCfxUCubEksDT4slMsh0dK")
						.isEnabled(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleAdmin))
						.build();

				userRepository.saveAll(List.of(user, admin));
			}
		};
	}
}
