package com.travelexperts.model;

import com.travelexperts.business.Customer;
import com.travelexperts.business.IEntity;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 723403 on 3/15/2016.
 */
public class CustomerProvider extends EntityProvider
{
    protected static IProvider provider = new CustomerProvider();

    //TABLE
    public static final String table = "Customers";
    //All columns
    public static final String[] allColumns =
            {
                    "CustomerId",
                    "CustFirstName",
                    "CustLastName",
                    "CustAddress",
                    "CustCity",
                    "CustProv",
                    "CustPostal",
                    "CustCountry",
                    "CustHomePhone",
                    "CustBusPhone",
                    "CustEmail",
                    "AgentId",
                    "UserName",
                    "Password"
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
                    allColumns[12] + ", " +
                    allColumns[13] +
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
                       allColumns[12] + " = ?, " +
                       allColumns[13] + " = ? "  +
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
            "AND "   + allColumns[12] + " = ? "  +
            "AND "   + allColumns[13] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        return provider.FetchAll(sql);
    }

    public static Customer GetById(int id)
    {
        String sql = getById;
        return (Customer) provider.Fetch(sql, id);
    }

    //GET WHERE OVERLOADS///////////////////////////////////////////////////////////////////////////////
    public static ArrayList GetWhere(String col, String val)
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, int val)
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, boolean val)
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, java.sql.Date val)
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, BigDecimal val)
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(Map<String,String> map)
    {
        String sql = prepWhere(map);
        return provider.FetchAll(sql);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String prepWhere(String col)
    {
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

    private static String prepWhere(Map<String, String> map)
    {
        String sql =
                "SELECT " + String.join(", ", allColumns) + " " +
                "FROM " + table + " " +
                "WHERE ";

        int i;
        int params = 0;
        for (i = 0; i < allColumns.length; i++)
        {
            if(map.containsKey(allColumns[i]))
            {
                if(params != 0) sql += " AND ";
                System.out.println(allColumns[i] + " = " + map.get(allColumns[i]));
                sql += allColumns[i] + " = '" + map.get(allColumns[i]) +"'";
                params++;
            }
        }
        System.out.println(sql);
        return sql;
    }

    public static boolean Add(Customer customer)
    {
        String sql = insert;
        return provider.Insert(sql, customer);
    }

    public static boolean Modify(Customer newCustomer, Customer oldCustomer)
    {
        String sql = update;
        return provider.Update(sql, newCustomer, oldCustomer);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt(i++));
        customer.setCustFirstName(rs.getString(i++));
        customer.setCustLastName(rs.getString(i++));
        customer.setCustAddress(rs.getString(i++));
        customer.setCustCity(rs.getString(i++));
        customer.setCustProv(rs.getString(i++));
        customer.setCustPostal(rs.getString(i++));
        customer.setCustCountry(rs.getString(i++));
        customer.setCustHomePhone(rs.getString(i++));
        customer.setCustBusPhone(rs.getString(i++));
        customer.setCustEmail(rs.getString(i++));
        customer.setAgentId(rs.getInt(i++));
        customer.setUserName(rs.getString(i++));
        customer.setPassword(rs.getString(i++));
        return customer;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        int i = 1;
        Customer customer = (Customer)entity;
        stmt.setString(i++, customer.getCustFirstName());
        stmt.setString(i++, customer.getCustLastName());
        stmt.setString(i++, customer.getCustAddress());
        stmt.setString(i++, customer.getCustCity());
        stmt.setString(i++, customer.getCustProv());
        stmt.setString(i++, customer.getCustPostal());
        stmt.setString(i++, customer.getCustCountry());
        stmt.setString(i++, customer.getCustHomePhone());
        stmt.setString(i++, customer.getCustBusPhone());
        stmt.setString(i++, customer.getCustEmail());
        stmt.setInt(i++, customer.getAgentId());
        stmt.setString(i++, customer.getUserName());
        stmt.setString(i++, customer.getPassword());
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        int i = 1;
        Customer newCustomer = (Customer) newEntity;
        Customer oldCustomer = (Customer)oldEntity;
        stmt.setString(i++, newCustomer.getCustFirstName());
        stmt.setString(i++, newCustomer.getCustLastName());
        stmt.setString(i++, newCustomer.getCustAddress());
        stmt.setString(i++, newCustomer.getCustCity());
        stmt.setString(i++, newCustomer.getCustProv());
        stmt.setString(i++, newCustomer.getCustPostal());
        stmt.setString(i++, newCustomer.getCustCountry());
        stmt.setString(i++, newCustomer.getCustHomePhone());
        stmt.setString(i++, newCustomer.getCustBusPhone());
        stmt.setString(i++, newCustomer.getCustEmail());
        stmt.setInt(i++, newCustomer.getAgentId());
        stmt.setString(i++, newCustomer.getUserName());
        stmt.setString(i++, newCustomer.getPassword());
        stmt.setInt(i++, oldCustomer.getCustomerId());
        stmt.setString(i++, oldCustomer.getCustFirstName());
        stmt.setString(i++, oldCustomer.getCustLastName());
        stmt.setString(i++, oldCustomer.getCustAddress());
        stmt.setString(i++, oldCustomer.getCustCity());
        stmt.setString(i++, oldCustomer.getCustProv());
        stmt.setString(i++, oldCustomer.getCustPostal());
        stmt.setString(i++, oldCustomer.getCustCountry());
        stmt.setString(i++, oldCustomer.getCustHomePhone());
        stmt.setString(i++, oldCustomer.getCustBusPhone());
        stmt.setString(i++, oldCustomer.getCustEmail());
        stmt.setInt(i++, oldCustomer.getAgentId());
        stmt.setString(i++, oldCustomer.getUserName());
        stmt.setString(i++, oldCustomer.getPassword());
        return stmt;
    }
}
