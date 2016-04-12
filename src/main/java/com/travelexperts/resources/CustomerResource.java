package com.travelexperts.resources;

import com.google.gson.reflect.TypeToken;
import com.travelexperts.business.Booking;
import com.travelexperts.business.Customer;
import com.travelexperts.json.JsonGenerator;
import com.travelexperts.json.JsonResult;
import com.travelexperts.model.BookingProvider;
import com.travelexperts.model.CustomerProvider;

import javax.lang.model.element.Parameterizable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by 723403 on 4/8/2016.
 */

@Path("user")
public class CustomerResource
{
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    /**
     * Rest Service for logging a user
     * @param version The application version
     * @param username
     * @param password
     * @returns the logged in user
     */
    @POST
    @Path("authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(
            @DefaultValue("1") @QueryParam("version") int version,
            @FormParam("UserName") String username,
            @FormParam("Password") String password)
    {
        String result = null;
        try {
            switch (version) {
                case 1:
                    if (username != null || password != null ) {
                        Map map = new HashMap<String,String>();
                        map.put("UserName",username);
                        map.put("Password",password);
                        //Search Database
                        Customer cust = (Customer) CustomerProvider.GetWhere(map).get(0);
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", cust);
                        result = JsonGenerator.generateSuccessJson(cust.getCustFirstName() + " " +
                                cust.getCustLastName() + " Logged In");
                    } else {
                        throw new Exception("Invalid Parameters");
                    }
                    break;
                default:
                    throw new Exception("Invalid Version");
            }
        }
        catch (Exception e)
        {
            result = JsonGenerator.generateErrorJson(e);
        }
        return result;
    }

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public String logout(
            @DefaultValue("1") @QueryParam("version") int version)
    {
        String result = null;
        try {
            switch (version) {
                case 1:
                    if(Authenication.Unauthenticate(request))
                    {
                        result = JsonGenerator.generateSuccessJson("Logged out");
                    }
                    break;
                default:
                    throw new Exception("Invalid Version");
            }
        }
        catch (Exception e)
        {
            result = JsonGenerator.generateErrorJson(e);
        }
        return result;
    }


    @POST
    @Path("register")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String register(
        @DefaultValue("1") @QueryParam("version") int version,
        @FormParam("customer") String inputData)
    {
        String result;
        try {
            switch (version) {
                case 1:
                    Customer cust = (Customer)JsonGenerator.generateTofromJson(inputData, Customer.class);
                    if (cust.getClass().isInstance(Customer.class))
                    {
                        if (CustomerProvider.Add(cust))
                        {
                            result = JsonGenerator.generateSuccessJson("Registration Successful");
                        } else {
                            throw new Exception(CustomerProvider.Message);
                        }

                    } else {
                        throw new Exception("Invalid data");
                    }
                    break;
                default:
                    throw new Exception("Invalid version");
            }
        } catch (Exception e) {
            result = JsonGenerator.generateErrorJson(e);
        }

        return result;
    }

    @GET
    @Path("bookings")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookings(
            @DefaultValue("1") @QueryParam("version") int version)
    {
        String result;
        try{
            Customer cust = Authenication.Authenicate(request);
            if(cust != null){
                int user = cust.getCustomerId();
                ArrayList bookings = BookingProvider.GetWhere("CustomerId",user);
                result = JsonGenerator.generateJson(bookings);
            }
            else{
                throw new Exception("Please Login to View Bookings");
            }
            //may need to use this to pass in an arraylist
            //result = JsonGenerator.generateJson(bookings, new TypeToken<ArrayList<Booking>>(){});
        } catch (Exception e){
            //result = JsonGenerator.generateJson("Errors");
            result = JsonGenerator.generateErrorJson(e);
        }
        return result;
    }

    @GET
    @Path("page")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPage(
            @DefaultValue("1") @QueryParam("version") int version)
    {
        String result = null;
        try {
            switch (version)
            {
                case 1:
                    Customer cust = Authenication.Authenicate(request);
                    if (cust == null)
                    {
                        result = JsonGenerator.generateSuccessJson("login.html");
                    } else {
                        result = JsonGenerator.generateJson(cust);
                    }
                    break;
                default:
                    throw new Exception("Invalid Version");
            }
        }
        catch (Exception e)
        {
            result = JsonGenerator.generateErrorJson(e);
        }
        return result;
    }
}
