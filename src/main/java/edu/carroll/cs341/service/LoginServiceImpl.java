package edu.carroll.cs341.service;

import java.util.List;

import edu.carroll.cs341.jpa.model.Login;
import edu.carroll.cs341.jpa.repo.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepo;

    public LoginServiceImpl(LoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    /**
     * Given a loginForm, determine if the information provided is valid, and the user exists in the system.
     *
     * @param username - Username of the person attempting to login
     * @param password - Raw password provided by the user logging in
     * @return true if data exists and matches what's on record, false otherwise
     */
    @Override
    public boolean validateUser(String username, String password) {
        // Always do the lookup in a case-insensitive manner (lower-casing the data).
        List<Login> users = loginRepo.findByUsernameIgnoreCase(username);

        // We expect 0 or 1, so if we get more than 1, bail out as this is an error we don't deal with properly.
        if (users.size() != 1)
            return false;
        Login u = users.getFirst();
        // XXX - Using Java's hashCode is wrong on SO many levels, but is good enough for demonstration purposes.
        // NEVER EVER do this in production code!
        final String userProvidedHash = Integer.toString(password.hashCode());
        if (!u.getHashedPassword().equals(userProvidedHash))
            return false;

        // User exists, and the provided password matches the hashed password in the database.
        return true;
    }
}
