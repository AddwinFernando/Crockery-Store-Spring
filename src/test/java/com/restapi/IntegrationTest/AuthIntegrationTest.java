package com.restapi.IntegrationTest;

import com.restapi.request.LoginRequest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegrationTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void loadContext() throws JSONException {
        //Arrange
        LoginRequest loginRequest = new LoginRequest("Test", "Test");

        //Act
        String response = testRestTemplate.postForObject("/api/auth/login", loginRequest,
                String.class);

        //Assert
        JSONAssert.assertEquals("{data:{id:21,username:Test,name:TestName,role:USER}}",
                response,false);
    }
}
