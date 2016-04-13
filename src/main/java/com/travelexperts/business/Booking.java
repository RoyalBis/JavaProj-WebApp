package com.travelexperts.business;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 723403 on 3/29/2016.
 */
public class Booking implements IEntity
{
    private int BookingId;
    private Date BookingDate;
    private String BookingNo;
    private double TravelerCount;
    private int CustomerId;
    private String TripTypeId;
    private int PackageId;
    private ArrayList<BookingDetails> BookingDetails = new ArrayList<>();
    
    public ArrayList<BookingDetails> getBookingDetails() { 
        return BookingDetails; 
    }
    
    public void setBookingDetails(ArrayList<BookingDetails> bookingdetails) { 
        BookingDetails = bookingdetails; 
    }

    public int getBookingId()
    {
        return BookingId;
    }

    public void setBookingId(int bookingId)
    {
        BookingId = bookingId;
    }

    public Date getBookingDate()
    {
        return BookingDate;
    }

    public void setBookingDate(Date bookingDate)
    {
        BookingDate = bookingDate;
    }

    public String getBookingNo()
    {
        return BookingNo;
    }

    public void setBookingNo(String bookingNo)
    {
        BookingNo = bookingNo;
    }

    public double getTravelerCount()
    {
        return TravelerCount;
    }

    public void setTravelerCount(double travelerCount)
    {
        TravelerCount = travelerCount;
    }

    public int getCustomerId()
    {
        return CustomerId;
    }

    public void setCustomerId(int customerId)
    {
        CustomerId = customerId;
    }

    public String getTripTypeId()
    {
        return TripTypeId;
    }

    public void setTripTypeId(String tripTypeId)
    {
        TripTypeId = tripTypeId;
    }

    public int getPackageId()
    {
        return PackageId;
    }

    public void setPackageId(int packageId)
    {
        PackageId = packageId;
    }

    @Override
    public String toString()
    {
        return "Bookings{" +
               "BookingId=" + BookingId +
               ", BookingDate=" + BookingDate +
               ", BookingNo='" + BookingNo + '\'' +
               ", TravelerCount=" + TravelerCount +
               ", CustomerId=" + CustomerId +
               ", TripTypeId='" + TripTypeId + '\'' +
               ", PackageId=" + PackageId +
               '}';
    }

    @Override
    public int getId()
    {
        return getBookingId();
    }

    @Override
    public void setId(int id)
    {
        setBookingId(id);
    }
}
