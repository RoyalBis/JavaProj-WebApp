package com.travelexperts.model;

import com.travelexperts.business.BookingDetails;
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
public class BookingDetailsProvider extends EntityProvider
{
    protected static IProvider provider = new CustomerProvider();

    //TABLE
    public static final String table = "BookingDetails";
    //All columns
    public static final String[] allColumns =
            {
                    "BookingDetailId",
                    "ItineraryNo",
                    "TripStart",
                    "TripEnd",
                    "Description",
                    "Destination",
                    "BasePrice",
                    "AgencyCommission",
                    "BookingId",
                    "RegionId",
                    "ClassId",
                    "FeeId",
                    "ProductSupplierId",
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
                    allColumns[1]  + ", " +
                    allColumns[2]  + ", " +
                    allColumns[3]  + ", " +
                    allColumns[4]  + ", " +
                    allColumns[5]  + ", " +
                    allColumns[6]  + ", " +
                    allColumns[7]  + ", " +
                    allColumns[8]  + ", " +
                    allColumns[9]  + ", " +
                    allColumns[10] + ", " +
                    allColumns[11] + ", " +
                    allColumns[12] +
                    ") " +
                    "Values(" + repeat("?", allColumns.length - 1) + ")";

    private static String update =
            "UPDATE " + table + " " +
            "SET " +
            allColumns[1]  + " = ?, " +
            allColumns[2]  + " = ?, " +
            allColumns[3]  + " = ?, " +
            allColumns[4]  + " = ?, " +
            allColumns[5]  + " = ?, " +
            allColumns[6]  + " = ?, " +
            allColumns[7]  + " = ?, " +
            allColumns[8]  + " = ?, " +
            allColumns[9]  + " = ?, " +
            allColumns[10] + " = ?, " +
            allColumns[11] + " = ?, " +
            allColumns[12] + " = ? "  +
            "WHERE " + allColumns[0]  + " = ? "  +
            "AND "   + allColumns[1]  + " = ? "  +
            "AND "   + allColumns[2]  + " = ? "  +
            "AND "   + allColumns[3]  + " = ? "  +
            "AND "   + allColumns[4]  + " = ? "  +
            "AND "   + allColumns[5]  + " = ? "  +
            "AND "   + allColumns[6]  + " = ? "  +
            "AND "   + allColumns[7]  + " = ? "  +
            "AND "   + allColumns[8]  + " = ? "  +
            "AND "   + allColumns[9]  + " = ? "  +
            "AND "   + allColumns[10] + " = ? "  +
            "AND "   + allColumns[11] + " = ? "  +
            "AND "   + allColumns[12] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        return provider.FetchAll(sql);
    }

    public static BookingDetails GetById(int id)
    {
        String sql = getById;
        return (BookingDetails) provider.Fetch(sql, id);
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

    public static boolean Add(BookingDetails details)
    {
        String sql = insert;
        return provider.Insert(sql, details);
    }

    public static boolean Modify(BookingDetails newDetails, BookingDetails oldDetails)
    {
        String sql = update;
        return provider.Update(sql, newDetails, oldDetails);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        BookingDetails details = new BookingDetails();
        details.setBookingDetailId(rs.getInt(i++));
        details.setItineraryNo(rs.getDouble(i++));
        details.setTripStart(rs.getDate(i++));
        details.setTripEnd(rs.getDate(i++));
        details.setDescription(rs.getString(i++));
        details.setDestination(rs.getString(i++));
        details.setBasePrice(rs.getBigDecimal(i++));
        details.setAgencyCommission(rs.getBigDecimal(i++));
        details.setBookingId(rs.getInt(i++));
        details.setRegionId(rs.getString(i++));
        details.setClassId(rs.getString(i++));
        details.setFeeId(rs.getString(i++));
        details.setProductSupplierId(rs.getInt(i++));
        return details;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        int i = 1;
        BookingDetails details = (BookingDetails)entity;
        stmt.setDouble(i++, details.getItineraryNo());
        stmt.setDate(i++, (Date) details.getTripStart());
        stmt.setDate(i++, (Date) details.getTripEnd());
        stmt.setString(i++, details.getDescription());
        stmt.setString(i++, details.getDestination());
        stmt.setBigDecimal(i++, details.getBasePrice());
        stmt.setBigDecimal(i++, details.getAgencyCommission());
        stmt.setInt(i++, details.getBookingId());
        stmt.setString(i++, details.getRegionId());
        stmt.setString(i++, details.getClassId());
        stmt.setString(i++, details.getFeeId());
        stmt.setInt(i++, details.getProductSupplierId());
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        int i = 1;
        BookingDetails newDetails = (BookingDetails)newEntity;
        BookingDetails oldDetails = (BookingDetails)oldEntity;
        stmt.setDouble(i++, newDetails.getItineraryNo());
        stmt.setDate(i++, (Date) newDetails.getTripStart());
        stmt.setDate(i++, (Date) newDetails.getTripEnd());
        stmt.setString(i++, newDetails.getDescription());
        stmt.setString(i++, newDetails.getDestination());
        stmt.setBigDecimal(i++, newDetails.getBasePrice());
        stmt.setBigDecimal(i++, newDetails.getAgencyCommission());
        stmt.setInt(i++, newDetails.getBookingId());
        stmt.setString(i++, newDetails.getRegionId());
        stmt.setString(i++, newDetails.getClassId());
        stmt.setString(i++, newDetails.getFeeId());
        stmt.setInt(i++, newDetails.getProductSupplierId());
        stmt.setInt(i++, oldDetails.getBookingDetailId());
        stmt.setDouble(i++, oldDetails.getItineraryNo());
        stmt.setDate(i++, (Date) oldDetails.getTripStart());
        stmt.setDate(i++, (Date) oldDetails.getTripEnd());
        stmt.setString(i++, oldDetails.getDescription());
        stmt.setString(i++, oldDetails.getDestination());
        stmt.setBigDecimal(i++, oldDetails.getBasePrice());
        stmt.setBigDecimal(i++, oldDetails.getAgencyCommission());
        stmt.setInt(i++, oldDetails.getBookingId());
        stmt.setString(i++, oldDetails.getRegionId());
        stmt.setString(i++, oldDetails.getClassId());
        stmt.setString(i++, oldDetails.getFeeId());
        stmt.setInt(i++, oldDetails.getProductSupplierId());
        return stmt;
    }
}
