package com.hackatron.integration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by premik91 on 11/10/16.
 */
public class Hackathon {

    

    public static void main(String[] args) throws IOException {

        /* sort of works>
        new callMBills().getBillsInLast24h();
        new callToshl().getExpenses(DateTime.now());
        
        Bill test = new Bill();
        new callToshl().addExpense(test);
        */

        new callSplitwise().oauth();

    }

    //TODO: check based on zoi
    ArrayList<Bill> addOnlyNewBills(ArrayList<Bill> billsAll, ArrayList<Bill> billsLast24h) {
         for(Bill b : billsLast24h){
             if (!billsAll.contains(b) && b.getIsPaid())
                 billsAll.add(b);
         }
        return billsAll;
    }
    
    
    void getStatus() {
//        Client client = ClientBuilder.newClient();
//        Response response = client.target("https://private-anon-2189f93165-mbills.apiary-mock.com/undefined")
//                .request(MediaType.TEXT_PLAIN_TYPE)
//                .header("Authorization", "Basic NzlmNjg5ODYtNmY2Yi00MGEwLTk4YjYtNDE3ZDMwMmRlYzg1LjIxMzEyMzEyLjE0NjIyNzE3NzE6ZmMzM2Y4OWYxYmFmY2UwMmQ5NzQ1Mjc5MzVjNmQ3NDA3NDdjNmRkYjQyMjM3MWJlZmFmZTk4YmJmMThjMGQyNg==")
//                .get();
//

    }


}
