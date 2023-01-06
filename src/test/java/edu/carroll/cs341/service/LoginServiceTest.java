package edu.carroll.cs341.service;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.util.List;

import edu.carroll.cs341.jpa.model.Login;
import edu.carroll.cs341.jpa.repo.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginServiceTest {
    private static final String username = "testuser";
    private static final String password = "testpass";

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginRepository loginRepo;

    private Login fakeUser = new Login(username, password);

    @BeforeEach
    public void beforeTest() {
        assertNotNull("loginRepository must be injected", loginRepo);
        assertNotNull("loginService must be injected", loginService);

        // Ensure dummy record is in the DB
        final List<Login> users = loginRepo.findByUsernameIgnoreCase(username);
        if (users.isEmpty())
            loginRepo.save(fakeUser);
    }

    @Test
    public void validateUserSuccessTest() {
        assertTrue("validateUserSuccessTest: should succeed using the same user/pass info", loginService.validateUser(username, password));
    }

    @Test
    public void validateUserExistingUserInvalidPasswordTest() {
        assertFalse("validateUserExistingUserInvalidPasswordTest: should fail using a valid user, invalid pass", loginService.validateUser(username, password + "extra"));
    }

    @Test
    public void validateUserInvalidUserValidPasswordTest() {
        assertFalse("validateUserInvalidUserValidPasswordTest: should fail using an invalid user, valid pass", loginService.validateUser(username + "not", password));
    }

    @Test
    public void validateUserInvalidUserInvalidPasswordTest() {
        assertFalse("validateUserInvalidUserInvalidPasswordTest: should fail using an invalid user, valid pass", loginService.validateUser(username + "not", password + "extra"));
    }
}
