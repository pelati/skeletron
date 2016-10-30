package com.jami.toshl.rest;

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

import com.google.common.base.Strings;
import com.hackatron.integration.Bill;
import com.hackatron.integration.DailyBills;
import com.hackatron.integration.Entry;
import com.jami.json.JsonUtils;
import com.jami.rest.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/toshl")
public class ToshlFacade {

    static String urlToshl = "https://api.toshl.com/entries";
    static String callExpenses = "/timeline?from=startDate&to=endDate&type=expense&group=day";
    static String userID = "3db76930-9278-4b5b-a373-908080962b4508eb5ffb-5b54-40af-b0b0-81d2b887dad5";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<DailyBills>> create(@BeanParam ToshlRequestParams toshlRequestParams, Bill bill) {
        // convert
        if (Strings.isNullOrEmpty(bill.getDate())) {
            bill = new Bill(bill.getAmount());
        }
        try {
            log.info("Sending bill to toshl: '{}'", JsonUtils.serialize(bill));
        } catch (JAXBException e) {
            // ignore
        }
        addExpense(bill);
        List<DailyBills> bills = getExpenses(DateTime.now().minusDays(1), DateTime.now());
        return Response.<List<DailyBills>> builder()
                .body(bills)
                .statusCode(200)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response<List<DailyBills>> get(@BeanParam ToshlRequestParams toshlRequestParams) {
        List<DailyBills> bills = getExpenses(DateTime.now().minusDays(5), DateTime.now());

        DailyBills latest = bills.get(0);
        latest.getEntries().add(0, Bill.of(-4.50, false, "Hood Burger"));

        return Response.<List<DailyBills>> builder()
                .body(bills)
                .statusCode(200)
                .build();
    }

    private List<DailyBills> getExpenses(DateTime startDate, DateTime endDate) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        String last = endDate.toString(dtf);
        String first = startDate.minusDays(1).toString(dtf);

        String url = urlToshl.concat(callExpenses).replace("startDate", first).replace("endDate", last);

        Client client = ClientBuilder.newClient();
        javax.ws.rs.core.Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + userID)
                .get();

        String expenses = response.readEntity(String.class);
        log.info(expenses);

        try {
            return JsonUtils.deserializeToList(expenses, DailyBills.class);
        } catch (JAXBException e) {
            log.error("Error deserializing response!", e);
        }
        return Collections.emptyList();
    }

    private void addExpense(Bill bill) {
        Entry newMBill = new Entry(bill);
        String jsonString = null;
        try {
            jsonString = JsonUtils.serialize(newMBill);
        } catch (JAXBException e) {
            log.error("Error serializin response!", e);
        }

        Client client = ClientBuilder.newClient();
        client.target(urlToshl)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + userID)
                .post(Entity.entity(jsonString, "application/json"));
    }

}