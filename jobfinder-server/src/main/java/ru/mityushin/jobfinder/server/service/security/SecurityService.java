package ru.mityushin.jobfinder.server.service.security;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
