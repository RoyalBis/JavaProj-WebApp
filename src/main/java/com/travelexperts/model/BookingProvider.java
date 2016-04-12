package com.travelexperts.model;

import com.travelexperts.business.Booking;
import com.travelexperts.business.IEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/29/2016.
 */
public class BookingProvider extends EntityProvider
{
    protected static IProvider provider = new BookingProvider();

    //TABLE
    public static final String table = "Bookings";
    //All columns
    public static final String[] allColumns =
            {
                    "BookingId",
                    "BookingDate",
                    "BookingNo",
                    "TravelerCount",
                    "CustomerId",
                    "TripTypeId",
                    "PackageId"
            };

    //SQL STATEMENTS
    private static String getAll =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " ORDER BY " + allColumns[0];
    private static String getById =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " " +
            "WHERE " + allColumns[0] + " = ?";
    private static String insert =
            "INSERT INTO " + table + " " +
            "(" +
            allColumns[1] + ", " +
            allColumns[2] + ", " +
            allColumns[3] + ", " +
            allColumns[4] + ", " +
            allColumns[5] + ", " +
            allColumns[6] +
            ") " +
            "Values(" + repeat("?", allColumns.length - 1) + ")";
    private static String update =
            "UPDATE " + table + " " +
            "SET " +
            allColumns[1] + " = ?, " +
            allColumns[2] + " = ?, " +
            allColumns[3] + " = ?, " +
            allColumns[4] + " = ?, " +
            allColumns[5] + " = ?, " +
            allColumns[6] + " = ? " +
            "WHERE " + allColumns[0] + " = ? " +
            "AND "   + allColumns[1] + " = ? " +
            "AND "   + allColumns[2] + " = ? " +
            "AND "   + allColumns[3] + " = ? " +
            "AND "   + allColumns[4] + " = ? " +
            "AND "   + allColumns[5] + " = ? " +
            "AND "   + allColumns[6] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        return provider.FetchAll(sql);
    }

    public static Booking GetById(int id)
    {
        String sql = getById;
        return (Booking) provider.Fetch(sql, id);
    }

    //GET WHERE OVERLOADS///////////////////////////////////////////////////////////////////////////////
    public static ArrayList GetWhere(String col, String val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, int val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, boolean val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, java.sql.Date val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, BigDecimal val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String prepWhere(String col){
        int i;
        for (i = 0; i < allColumns.length; i++)
        {
            if(col.equals(allColumns[i]))
            {
                break;
            }
        }
        String sql =
                "SELECT " + String.join(", ", allColumns) + " " +
                "FROM " + table + " " +
                "WHERE " + allColumns[i] + "  = ?";
        return sql;
    }

    public static boolean Add(Booking booking)
    {
        String sql = insert;
        return provider.Insert(sql, booking);
    }

    public static boolean Modify(Booking newBooking, Booking oldBooking)
    {
        String sql = update;
        return provider.Update(sql, newBooking, oldBooking);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt(i++));
        booking.setBookingDate(rs.getDate(i++));
        booking.setBookingNo(rs.getString(i++));
        booking.setTravelerCount(rs.getDouble(i++));
        booking.setCustomerId(rs.getInt(i++));
        booking.setTripTypeId(rs.getString(i++));
        booking.setPackageId(rs.getInt(i++));
        return booking;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        int i = 1;
        Booking booking = (Booking)entity;
        stmt.setDate(i++, (Date) booking.getBookingDate());
        stmt.setString(i++, booking.getBookingNo());
        stmt.setDouble(i++, booking.getTravelerCount());
        stmt.setInt(i++, booking.getCustomerId());
        stmt.setString(i++, booking.getTripTypeId());
        stmt.setInt(i++, booking.getPackageId());
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        int i =1;
        Booking newBooking = (Booking)newEntity;
        Booking oldBooking = (Booking)oldEntity;
        stmt.setDate(i++, (Date) newBooking.getBookingDate());
        stmt.setString(i++, newBooking.getBookingNo());
        stmt.setDouble(i++, newBooking.getTravelerCount());
        stmt.setInt(i++, newBooking.getCustomerId());
        stmt.setString(i++, newBooking.getTripTypeId());
        stmt.setInt(i++, newBooking.getPackageId());
        stmt.setInt(i++, oldBooking.getBookingId());
        stmt.setDate(i++, (Date) oldBooking.getBookingDate());
        stmt.setString(i++, oldBooking.getBookingNo());
        stmt.setDouble(i++, oldBooking.getTravelerCount());
        stmt.setInt(i++, oldBooking.getCustomerId());
        stmt.setString(i++, oldBooking.getTripTypeId());
        stmt.setInt(i++, oldBooking.getPackageId());
        return stmt;
    }
}
