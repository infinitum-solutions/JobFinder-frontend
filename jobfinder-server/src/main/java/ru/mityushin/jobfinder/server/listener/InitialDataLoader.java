package ru.mityushin.jobfinder.server.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mityushin.jobfinder.server.model.Person;
import ru.mityushin.jobfinder.server.model.Role;
import ru.mityushin.jobfinder.server.repo.PersonRepository;
import ru.mityushin.jobfinder.server.repo.RoleRepository;
import ru.mityushin.jobfinder.server.service.role.RoleService;
import ru.mityushin.jobfinder.server.util.enums.SecurityRole;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final PersonRepository personRepository;
    private final PasswordEncoder encoder;
    private final Logger log;

    private boolean hasAlreadySetup = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasAlreadySetup) {
            for (SecurityRole securityRole : SecurityRole.values()) {
                createRoleIfDoesntExists(securityRole.name());
            }
            createPersonIfDoesntExists("admin",
                    UUID.fromString("00000000-0000-0000-0000-000000000000"),
                    roleService.getAdminRoles());
            createPersonIfDoesntExists("moderator",
                    UUID.fromString("00000000-0000-0000-0000-000000000001"),
                    roleService.getModeratorRoles());
            createPersonIfDoesntExists("user",
                    UUID.fromString("00000000-0000-0000-0000-000000000002"),
                    roleService.getUserRoles());
            hasAlreadySetup = true;
        }
    }

    private Person createPersonIfDoesntExists(String username, UUID uuid, Set<Role> roles) {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            log.info("Creating person {}.", username);
            return personRepository.save(Person.builder()
                    .uuid(uuid)
                    .username(username)
                    .password(encoder.encode("password"))
                    .roles(roles)
                    .deleted(Boolean.FALSE)
                    .locked(Boolean.FALSE)
                    .enabled(Boolean.TRUE)
                    .build());
        }
        log.info("Can't create person {}. Reason: person is already exists.", username);
        return person;
    }

    private Role createRoleIfDoesntExists(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            log.info("Creating role {}.", name);
            return roleRepository.save(Role.builder()
                    .name(name)
                    .build());
        }
        log.info("Can't create role {}. Reason: role is already exists.", name);
        return role;
    }
}
