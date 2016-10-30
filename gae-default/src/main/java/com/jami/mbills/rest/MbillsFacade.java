package com.jami.mbills.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackatron.integration.Bill;
import com.hackatron.integration.DailyBills;
import com.hackatron.integration.Entry;
import com.jami.json.JsonUtils;
import com.jami.rest.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/mbills")
public class MbillsFacade {

    // TODO: change mock to real
    static final String urlMBills = "https://private-anon-2189f93165-mbills.apiary-mock.com/MBillsWS";
    // static final String urlMBills = "https://hack.halcom.com/MBillsWS"; //https://api.mbills.com
    static final String callLast24h = "/API/v1/transaction/last24h";

    String username = "79f68986-6f6b-40a0-98b6-417d302dec85.5656373577.1462271771";
    String password = "02f94f5f42c72e71adc43ccde289661be281e9779f45faaad9eca19541445f0b";

    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<DailyBills>> create(@BeanParam MbillsRequestParams mbillsRequestParams, Bill bill) {
        // convert
        addExpense(new Bill(bill.getAmount()));
        List<DailyBills> bills = getExpenses(DateTime.now().minusDays(1), DateTime.now());
        return Response.<List<DailyBills>> builder()
                .body(bills)
                .statusCode(200)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<DailyBills>> get(@BeanParam MbillsRequestParams mbillsRequestParams) {
        List<DailyBills> bills = getExpenses(DateTime.now().minusDays(1), DateTime.now());
        return Response.<List<DailyBills>> builder()
                .body(bills)
                .statusCode(200)
                .build();
    }
    */

    static void getBillsInLast24h() throws UnsupportedEncodingException {
        String url = urlMBills.concat(callLast24h);
        Client client = ClientBuilder.newClient();
        javax.ws.rs.core.Response response = client.target(url)
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header("Authorization",
                        "Basic NzlmNjg5ODYtNmY2Yi00MGEwLTk4YjYtNDE3ZDMwMmRlYzg1LjIxMzEyMzEyLjE0NjIyNzE3NzE6ZmMzM2Y4OWYxYmFmY2UwMmQ5NzQ1Mjc5MzVjNmQ3NDA3NDdjNmRkYjQyMjM3MWJlZmFmZTk4YmJmMThjMGQyNg==")
                .get();

        System.out.println("status: " + response.getStatus());

        String bills24h = response.readEntity(String.class);
        ArrayList<Bill> bills = parseBills(bills24h);

    }

    private static ArrayList<Bill> parseBills(String jsonBills) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {
            String jsonString = jsonBills.replace("”", "\"");
            // System.out.println(jsonString);
            ArrayList<Bill> bills = mapper.readValue(jsonString, new TypeReference<List<Bill>>() {
            });

            System.out.println("New bills: " + bills.size());

            return bills;

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}