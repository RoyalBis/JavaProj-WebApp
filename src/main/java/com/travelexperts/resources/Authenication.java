package com.travelexperts.resources;

import com.travelexperts.business.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 723403 on 4/11/2016.
 */
public class Authenication
{
    public static Customer Authenicate(HttpServletRequest request) throws Exception
    {
        HttpSession session = request.getSession(false);
        if (session != null) {
            //System.out.println(session.getAttribute("user"));
            return (Customer) session.getAttribute("user");
        }
        else {
            return null;
        }
    }

    public static boolean Unauthenticate(HttpServletRequest request) throws Exception
    {
        HttpSession session = request.getSession(false);
        if(session != null) { session.invalidate(); }
        return true;
    }
}
