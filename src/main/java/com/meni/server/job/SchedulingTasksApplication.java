package com.meni.server.job;

import com.meni.server.model.ERole;
import com.meni.server.repo.Role;
import com.meni.server.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {

    public static void main(String[] args) {

        SpringApplication.run(SchedulingTasksApplication.class);
    }
//    private void addRolesTable() {
//        this.roleRepository.save(new Role(ERole.ROLE_USER));
//        this.roleRepository.save(new Role(ERole.ROLE_ADMIN));
//    }
}