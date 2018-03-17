package smartfocus.test.controller;

import com.smartfocus.test.SmartFocusApp;
import com.smartfocus.test.model.ErrorMessage;
import com.smartfocus.test.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SmartFocusApp.class)
@TestPropertySource(properties = {"management.port=0"})
public class UserControllerMockIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserService userService;

    /*Dummy test to show how Illegal Argument exception is handled
    in service layer though it may not happen for get all users.
    */
    @Test
    public void shouldReturn400ForGetUsers() {

        given(this.userService.
                getUsersDb()
        ).willThrow(new IllegalArgumentException());

        ResponseEntity<ErrorMessage> error = testRestTemplate.getForEntity(
                "/user", ErrorMessage.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), error.getBody().getErrorCode());
        Assert.assertEquals("Input arguments are not valid", error.getBody().getErrorMessage());
    }

    @Test
    public void shouldReturn500ForAnyRuntimeException() {

        given(this.userService.
                getUsersDb()
        ).willThrow(new RuntimeException());

        ResponseEntity<ErrorMessage> error = testRestTemplate.getForEntity(
                "/user", ErrorMessage.class);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, error.getStatusCode());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getBody().getErrorCode());
        Assert.assertEquals("Server error", error.getBody().getErrorMessage());
    }

    @Test
    public void shouldReturn500ForNullPointerException() {

        given(this.userService.
                getUsersDb()
        ).willThrow(new NullPointerException());

        ResponseEntity<ErrorMessage> error = testRestTemplate.getForEntity(
                "/user", ErrorMessage.class);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, error.getStatusCode());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getBody().getErrorCode());
        Assert.assertEquals("Server error", error.getBody().getErrorMessage());
    }

}
