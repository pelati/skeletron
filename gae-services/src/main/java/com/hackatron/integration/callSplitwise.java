package com.hackatron.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.scribe.builder.ServiceBuilder;
//import org.scribe.model.*;
//import org.scribe.oauth.OAuthService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by premik91 on 15/10/16.
 */
public class callSplitwise {
    
    static String urlSplitwise = "https://secure.splitwise.com/api/v3.0/";
    static String callFriends = "get_friends";
    
    static String consumerKey = "OAPgFK1OitT5pRX1nLuBLi5J4WcYH3WQR2XqR5NM";
    static String consumerSecret = "Mg3ZavqEkHCp1tg9YDGXcXRfkBoN1TwvCVkI2IRD";
    
    public void oauth() {
        /*
        OAuthService service = new ServiceBuilder()
                .provider(SplitwiseAPI.class)
                .signatureType(SignatureType.QueryString)
                .apiKey(consumerKey)
                .apiSecret(consumerSecret)
                .scope("API.Public")
                .build();

        Token requestToken = service.getRequestToken();

        //make your API calls
        OAuthRequest request = new OAuthRequest(Verb.GET, urlSplitwise.concat(callFriends));
        service.signRequest(requestToken, request);
        Response response = request.send();
        System.out.println(response.getBody());
        */
        
    }

    public ArrayList<Friend> getFriendsIOwe(ArrayList<Friend> friends) {
        ArrayList<Friend> friendsIOwe = new ArrayList<Friend>();

        System.out.println("Friends I owe: "+friends.size());
        
        for(Friend f : friends) {
            for(Balance b : f.getBalance()) {
                if (b.getCurrency_code().equals("EUR") && b.getAmount()!=0) {
                    friendsIOwe.add(f);
                    System.out.println(f.getFirst_name()+" "+f.getLast_name()+":\t"+f.getBalance().toString());
                }
            }
        }

        return friendsIOwe;
    }

    public ArrayList<Friend> getAllFriends(String jsonFriends) {
        /*
        String url = urlSplitwise.concat(callFriends);
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header("Authorization", "Basic "+userID)
                .get();

        System.out.println("status: " + response.getStatus());
        */

        //String jsonFriends = response.readEntity(String.class);
        System.out.println(jsonFriends);
        return parseFriends(jsonFriends);
    }
    
    public ArrayList<Friend> parseFriends(String jsonFriends) {
        /*
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        try {
            String jsonString = jsonFriends.replace("”","\"");
            ArrayList<Friend> friends = mapper.readValue(jsonString, new TypeReference<List<Bill>>() {
            });

            System.out.println("All friends: "+friends.size());

            return friends;

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        return null;
    }
    
    
    
    ///TODO: merge with mBills friends
    

}
