package com.travelexperts.resources;

import com.google.gson.reflect.TypeToken;
import com.travelexperts.business.Booking;
import com.travelexperts.business.BookingDetails;
import com.travelexperts.business.Customer;
import com.travelexperts.json.JsonGenerator;
import com.travelexperts.json.JsonResult;
import com.travelexperts.model.BookingDetailsProvider;
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
        System.out.println("WE ARE RUNNING LOGIN THING"); 
        try {
            switch (version) {
                case 1:
                    if (username != null || password != null ) {
                        Map map = new HashMap<String,String>();
                        map.put("UserName",username);
                        map.put("Password",password);
                        //Search Database
                        ArrayList custMap = CustomerProvider.GetWhere(map);
                        if(custMap.size() != 1)
                        {
                            throw new Exception("Invalid Username or Password");
                        }
                        Customer cust = (Customer) custMap.get(0);
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", cust);
                        result = JsonGenerator.generateJson(cust);
                    } else {
                        throw new Exception("Invalid Username or Password");
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
                    if(cust == null){
                        //Currently this always fails because I have no implementation to convert JSON to a customer
                        throw new Exception("Customer Registration is not yet Implemented, \nPlease Contact Mark Willms 780-781-9221");
                    }
                    System.out.println("Hello " + cust);
                    if (cust.getClass().isInstance(Customer.class))
                    {
                        if (CustomerProvider.Add(cust))
                        {
                            result = JsonGenerator.generateSuccessJson("Registration Successful");
                        } else {
                            throw new Exception(CustomerProvider.Message);
                        }

                    } else {
                        throw new Exception("Invalid Registration Info");
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
                
                ArrayList<Booking> bookings = BookingProvider.GetWhere("CustomerId",user);
                for(Booking book: bookings) {
                    ArrayList<BookingDetails> details = BookingDetailsProvider.GetWhere("BookingId", book.getBookingId());
                    book.setBookingDetails(details);
                }
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
    @Path("customer")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(
            @DefaultValue("1") @QueryParam("version") int version)
    {
        String result;
        System.out.println("WE ARE RUNNING CUSTOMER THING"); 
        try{
            Customer cust = Authenication.Authenicate(request);
            if(cust == null) {  
                System.out.println("SHITS FUCKED");
            }
            if(cust != null){
                int user = cust.getCustomerId();
                System.out.println("SHITS WORKING");
                ArrayList<Customer> customers = CustomerProvider.GetWhere("CustomerId", user);
                result = JsonGenerator.generateJson(customers);
            }
            else{
                throw new Exception("Please Login to View Profile");
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
