package ru.mityushin.jobfinder.server.service.userdetails;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mityushin.jobfinder.server.model.Person;
import ru.mityushin.jobfinder.server.repo.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private PersonRepository personRepository;
    private Logger log;

    @PostConstruct
    public void postConstruct() {
        log.error("FIXME: Remove 'test' user from UserDetailsServiceImpl::loadUserByUsername! Dirty hack!!!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("test")) {
            return new ExtendedUserDetails(
                    Person.builder()
                            .username(username)
                            .password(new BCryptPasswordEncoder().encode("password"))
                            .uuid(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                            .deleted(false)
                            .locked(false)
                            .enabled(true)
                            .build());
        }
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
        return new ExtendedUserDetails(person);
    }
}
