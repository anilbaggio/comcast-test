package com.comcast.lcs.controller;

/**
 *
 * @author anil.baggio
 */
import com.comcast.lcs.bean.InputBean;
import com.comcast.lcs.bean.OutputBean;
import com.comcast.lcs.bean.Value;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/task")
public class Controller {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() {
        throw new UnsupportedOperationException();
    }

    @POST
    @Path("/lcs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OutputBean computeLCS(InputBean ib) throws Exception {
        Value res = new Value();
        List<String> stringsList = new ArrayList();
        String inputRequest = ib.getInput();
        OutputBean ob = new OutputBean();

        //Convert input String to JSON
        JSONParser parser = new JSONParser();

        //if no input from the User
        if (inputRequest.isEmpty()) {
            Response.status(Response.Status.NOT_ACCEPTABLE);
            ob.setMessage("Empty Request");
        } else {
            JSONObject json = (JSONObject) parser.parse(inputRequest);
            JSONArray setOfStrings = (JSONArray) json.get("setOfStrings");

            //if setOfString is Empty
            if (setOfStrings.size() < 1) {
                Response.status(Response.Status.NO_CONTENT);
                ob.setMessage("No data found");

            } else {
                for (int i = 0; i < setOfStrings.size(); i++) {
                    JSONObject jsoObj = (JSONObject) setOfStrings.get(i);
                    stringsList.add((String) jsoObj.get("value"));
                }
                String result = computeLCS(stringsList);
                if (!result.isEmpty()) {
                    Response.status(Response.Status.OK);
                    res.setValue(result);
                    ob.setValue((List<Value>) res);
                } else {
                    // if no strings were common or unique
                    Response.status(Response.Status.NOT_FOUND);
                    ob.setMessage("No words were unique");
                }

            }
        }

        return ob;
    }
    // method to calculate the LCS from the given list

    private String computeLCS(List<String> setOfString) {
        // size of the list
        int n = setOfString.size();

        // getting the first word from the list as reference
        String s = setOfString.get(0);
        int len = s.length();

        String res = "";

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {

                // generating all possible substrings
                // of our reference string arr[0] i.e s
                String stem = s.substring(i, j);
                int k = 1;
                for (k = 1; k < n; k++) // Check if the generated string is
                // common to all words
                {
                    if (!setOfString.get(k).contains(stem)) {
                        break;
                    }
                }

                // If current substring is present in
                // all strings and its length is greater
                // than current result
                if (k == n && res.length() < stem.length()) {
                    res = stem;
                }
            }
        }

        return res;
    }

}
