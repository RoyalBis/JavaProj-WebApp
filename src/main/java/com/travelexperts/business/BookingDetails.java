package com.travelexperts.business;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 723403 on 3/29/2016.
 */
public class BookingDetails implements IEntity
{
    private int BookingDetailId;
    private double ItineraryNo;
    private Date TripStart;
    private Date TripEnd;
    private String Description;
    private String Destination;
    private BigDecimal BasePrice;
    private BigDecimal AgencyCommission;
    private int BookingId;
    private String RegionId;
    private String ClassId;
    private String FeeId;
    private int ProductSupplierId;

    public int getBookingDetailId()
    {
        return BookingDetailId;
    }

    public void setBookingDetailId(int bookingDetailId)
    {
        BookingDetailId = bookingDetailId;
    }

    public double getItineraryNo()
    {
        return ItineraryNo;
    }

    public void setItineraryNo(double itineraryNo)
    {
        ItineraryNo = itineraryNo;
    }

    public Date getTripStart()
    {
        return TripStart;
    }

    public void setTripStart(Date tripStart)
    {
        TripStart = tripStart;
    }

    public Date getTripEnd()
    {
        return TripEnd;
    }

    public void setTripEnd(Date tripEnd)
    {
        TripEnd = tripEnd;
    }

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public String getDestination()
    {
        return Destination;
    }

    public void setDestination(String destination)
    {
        Destination = destination;
    }

    public BigDecimal getBasePrice()
    {
        return BasePrice;
    }

    public void setBasePrice(BigDecimal basePrice)
    {
        BasePrice = basePrice;
    }

    public BigDecimal getAgencyCommission()
    {
        return AgencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission)
    {
        AgencyCommission = agencyCommission;
    }

    public int getBookingId()
    {
        return BookingId;
    }

    public void setBookingId(int bookingId)
    {
        BookingId = bookingId;
    }

    public String getRegionId()
    {
        return RegionId;
    }

    public void setRegionId(String regionId)
    {
        RegionId = regionId;
    }

    public String getClassId()
    {
        return ClassId;
    }

    public void setClassId(String classId)
    {
        ClassId = classId;
    }

    public String getFeeId()
    {
        return FeeId;
    }

    public void setFeeId(String feeId)
    {
        FeeId = feeId;
    }

    public int getProductSupplierId()
    {
        return ProductSupplierId;
    }

    public void setProductSupplierId(int productSupplierId)
    {
        ProductSupplierId = productSupplierId;
    }

    @Override
    public String toString()
    {
        return "BookingDetails{" +
               "BookingDetailId=" + BookingDetailId +
               ", ItineraryNo=" + ItineraryNo +
               ", TripStart=" + TripStart +
               ", TripEnd=" + TripEnd +
               ", Description='" + Description + '\'' +
               ", Destination='" + Destination + '\'' +
               ", BasePrice=" + BasePrice +
               ", AgencyCommission=" + AgencyCommission +
               ", BookingId=" + BookingId +
               ", RegionId='" + RegionId + '\'' +
               ", ClassId='" + ClassId + '\'' +
               ", FeeId='" + FeeId + '\'' +
               ", ProductSupplierId=" + ProductSupplierId +
               '}';
    }

    @Override
    public int getId()
    {
        return getBookingDetailId();
    }

    @Override
    public void setId(int id)
    {
        setBookingDetailId(id);
    }
}
