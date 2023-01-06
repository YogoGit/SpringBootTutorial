package edu.carroll.cs389.service;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.util.List;

import edu.carroll.cs389.jpa.model.Login;
import edu.carroll.cs389.jpa.repo.LoginRepository;
import edu.carroll.cs389.web.form.LoginForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginServiceImplTest {
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
        final LoginForm form = new LoginForm(username, password);
        assertTrue("validateUserSuccessTest: should succeed using the same user/pass info", loginService.validateUser(form));
    }

    @Test
    public void validateUserExistingUserInvalidPasswordTest() {
        final LoginForm form = new LoginForm(username, password + "extra");
        assertFalse("validateUserExistingUserInvalidPasswordTest: should fail using a valid user, invalid pass", loginService.validateUser(form));
    }

    @Test
    public void validateUserInvalidUserValidPasswordTest() {
        final LoginForm form = new LoginForm(username + "not", password);
        assertFalse("validateUserInvalidUserValidPasswordTest: should fail using an invalid user, valid pass", loginService.validateUser(form));
    }

    @Test
    public void validateUserInvalidUserInvalidPasswordTest() {
        final LoginForm form = new LoginForm(username + "not", password + "extra");
        assertFalse("validateUserInvalidUserInvalidPasswordTest: should fail using an invalid user, valid pass", loginService.validateUser(form));
    }
}
