package com.jami.user.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jami.base.TestFacadeBase;
import com.jami.rest.Response;
import com.jami.rest.RestApplication;
import com.jami.entity.Challenge;
import com.jami.entity.Offer;
import com.jami.entity.User;
import junitparams.JUnitParamsRunner;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import javax.ws.rs.core.Application;
import javax.xml.bind.JAXBException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static com.jami.json.JsonUtils.deserialize;
import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class UserFacadeTest extends TestFacadeBase {
    @InjectMocks
    private static UserFacade facade = new UserFacade();

    @Override
    protected Object facadeInstance() {
        return facade;
    }

    @Override
    protected Application applicationInstance() {
        return new RestApplication();
    }

    @Test
    @Ignore
    public void shouldSuccessfullyRegisterUserAndStoreItInDs() throws URISyntaxException, JAXBException {
        
        User user = User.builder()
                .id(1234L)
                .firstName("Luigi")
                .lastName("Pelati")
                .points(100)
                .offers(Arrays.asList(Offer.builder()
                        .dateFrom(new Date())
                        .dateTo(new Date())
                        .description("Buy 10 and get one free!")
                        .name("Hood Burger 10/11")
                        .discountPercent(11)
                        .id(123L)
                        .pointsCost(222)
                        .type("1")
                        .counter(33)
                        .build()))
                .challenges(Arrays.asList(Challenge.builder()
                        .counter(444)
                        .dateFrom(new Date())
                        .dateTo(new Date())
                        .description("Pay wit Hal mBills 10 consecutive days!")
                        .name("Paying Streak")
                        .id(321L)
                        .pointsValue(555)
                        .price(6)
                        .storeId(4321)
                        .type("2")
                        .build()))
                .build();

        // Given, When
        MockHttpResponse response = sendPostRequest("/users/register", new HashMap<String, String>(), null, null, null);

        // Then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        Response<User> userResponse = deserialize(response.getContentAsString(), new TypeReference<Response<User>>() {});
        assertThat(userResponse.getStatusCode()).isEqualTo(OK.getStatusCode());
        assertThat(userResponse.getBody().getId()).isNotNull();
    }

}
