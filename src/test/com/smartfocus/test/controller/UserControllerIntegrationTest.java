package smartfocus.test.controller;

import com.smartfocus.test.SmartFocusApp;
import com.smartfocus.test.model.User;
import com.smartfocus.test.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SmartFocusApp.class)
@TestPropertySource(properties = {"management.port=0"})
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void shouldReturn200ForGetUsers() {

        ResponseEntity<User[]> entity = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/user",User[].class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assert.assertNotNull(entity.getBody());

        Assert.assertEquals(userService.getUsersDb().get(0).toString(), entity.getBody()[0].toString());
        Assert.assertEquals(userService.getUsersDb().get(1).toString(), entity.getBody()[1].toString());
    }
}
