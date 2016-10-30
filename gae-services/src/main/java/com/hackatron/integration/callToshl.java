package com.hackatron.integration;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.jami.json.JsonUtils;

/**
 * Created by premik91 on 15/10/16.
 */
public class callToshl {

    static String urlToshl = "https://api.toshl.com/entries";
    static String callExpenses = "/timeline?from=startDate&to=endDate&type=expense&group=day";
    static String userID = "3db76930-9278-4b5b-a373-908080962b4508eb5ffb-5b54-40af-b0b0-81d2b887dad5";

    /*
     * String callAPI(String url, String apiCall) {
     * url = url.concat(apiCall);
     * Client client = ClientBuilder.newClient();
     * Response response = client.target(url)
     * .request(MediaType.TEXT_PLAIN_TYPE)
     * .header("Authorization", "Bearer T9cE5asGnuyYCCqIZFoWjFHvNbvVqHjl")
     * .get();
     * 
     * return response.readEntity(String.class);
     * }
     */

    // TODO: Toshl = neg., mBills = pos. for expense

    String getExpenses(DateTime startDate) {
        return getExpenses(startDate, DateTime.now());
    }

    private String getExpenses(DateTime startDate, DateTime endDate) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        String last = endDate.toString(dtf);
        String first = startDate.minusDays(1).toString(dtf);

        String url = urlToshl.concat(callExpenses).replace("startDate", first).replace("endDate", last);

        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + userID)
                .get();

        String expenses = response.readEntity(String.class);

        try {
            List<DailyBills> dailyBills = JsonUtils.deserializeToList(expenses, DailyBills.class);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return expenses;
    }

    public void addExpense(Bill bill) throws IOException, JAXBException {
        Entry newMBill = new Entry(bill);
        String jsonString = JsonUtils.serialize(newMBill);

        Client client = ClientBuilder.newClient();
        System.out.println(jsonString);
        Response response = client.target(urlToshl)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + userID)
                .post(Entity.entity(jsonString, "application/json"));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));

    }

}
