package com.sda.j97.guardian.configuration;

import com.sda.j97.guardian.model.ApplicationUser;
import com.sda.j97.guardian.model.ApplicationUserRole;
import com.sda.j97.guardian.repository.ApplicationUserRepository;
import com.sda.j97.guardian.repository.ApplicationUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialDataCreator implements ApplicationListener<ContextRefreshedEvent> {
    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "nimda";

    private final String ROLE_NAME_USER = "ROLE_USER";
    private final String ROLE_NAME_ADMIN = "ROLE_ADMIN";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Jeśli rola ADMIN nie istnieje w bazie, dodaj ją
        if(!applicationUserRoleRepository.existsByName(ROLE_NAME_ADMIN)){
            applicationUserRoleRepository.save(new ApplicationUserRole(null, ROLE_NAME_ADMIN));
        }
        // Jeśli rola UŻYTKOWNIK nie istnieje w bazie, dodaj ją
        if(!applicationUserRoleRepository.existsByName(ROLE_NAME_USER)){
            applicationUserRoleRepository.save(new ApplicationUserRole(null, ROLE_NAME_USER));
        }

        // Jeśli użytkownik ADMIN nie istnieje, dodaj go (trzeba mu dodać rolę admina i usera)
        if(!applicationUserRepository.existsByUsername(ADMIN_USERNAME)){
            Set<ApplicationUserRole> roles = new HashSet<>();
            Optional<ApplicationUserRole> roleAdmin = applicationUserRoleRepository.findByName(ROLE_NAME_ADMIN);
            if(roleAdmin.isPresent()){
                roles.add(roleAdmin.get());
            }
            Optional<ApplicationUserRole> roleUser = applicationUserRoleRepository.findByName(ROLE_NAME_USER);
            if(roleUser.isPresent()){
                roles.add(roleUser.get());
            }

            applicationUserRepository.save(new ApplicationUser(
                    null,
                    ADMIN_USERNAME,
                    bCryptPasswordEncoder.encode(ADMIN_PASSWORD),
                    "Administrator",
                    "Administratorski",
                    LocalDate.of(2020, 1, 1),
                    roles,
                    null,
                    null,
                    null,
                    null,
                    null));
        }
    }
}
