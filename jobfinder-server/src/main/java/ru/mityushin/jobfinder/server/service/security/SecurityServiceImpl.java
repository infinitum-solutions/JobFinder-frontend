package ru.mityushin.jobfinder.server.service.security;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private Logger log;

    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, Logger log) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.log = log;
    }

    @Override
    public String findLoggedInUsername() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails) userDetails).getUsername();
//        }
//        return null;
        log.debug(loggedInUser);
        return loggedInUser.getName();
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.debug(userDetails);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            log.debug(String.format("Auto login %s successfully!", username));
        }
    }
}
