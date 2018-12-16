package ru.mityushin.jobfinder.server.service.userdetails;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mityushin.jobfinder.server.model.Person;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class ExtendedUserDetails implements UserDetails, Identifiable<UUID> {

    Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        ZonedDateTime expire = person.getExpire();
        return (expire == null) || (expire.compareTo(ZonedDateTime.now()) > 0);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !person.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        ZonedDateTime expire = person.getCredentialsExpire();
        return (expire == null) || (expire.compareTo(ZonedDateTime.now()) > 0);
    }

    @Override
    public boolean isEnabled() {
        return person.getEnabled();
    }

    @Override
    public UUID getIdentifier() {
        return person.getUuid();
    }
}
