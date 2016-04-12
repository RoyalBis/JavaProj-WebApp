package com.travelexperts.model;

import com.travelexperts.business.IEntity;
import com.travelexperts.business.Supplier;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class SupplierProvider extends EntityProvider
{
    private static final IProvider provider = new SupplierProvider();

    //TABLE
    public static final String table = "Suppliers";
    //All columns
    public static final String[] allColumns =
            {
                    "SupplierId",
                    "SupName"
            };

    //SQL STATEMENTS
    private static final String getAll =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " ORDER BY " + allColumns[0];

    private static final String getById =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " " +
            "WHERE " + allColumns[0] + " = ?";

    private static final String getWhere =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " " +
            "WHERE ? = ?";

    private static final String insert =
            "INSERT INTO " + table + " " +
            "(" +
            allColumns[1] + ") " +
            "Values(" + repeat("?", allColumns.length - 1) + ")";

    private static final String update =
            "UPDATE " + table + " " +
            "SET " +
            allColumns[1] + " = ? " +
            "WHERE " + allColumns[0] + " = ? " +
            "AND "   + allColumns[1] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        entityList = provider.FetchAll(sql);
        return entityList;
    }

    public static Supplier GetById(int id)
    {
        String sql = getById;
        return (Supplier) provider.Fetch(sql, id);
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

    public static boolean Add(Supplier supplier)
    {
        String sql = insert;
        return provider.Insert(sql, supplier);
    }

    public static boolean Modify(Supplier newSupplier, Supplier oldSupplier)
    {
        String sql = update;
        return provider.Update(sql, newSupplier, oldSupplier);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(rs.getInt(1));
        supplier.setSupName(rs.getString(2));
        return supplier;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        Supplier supplier = (Supplier)entity;
        stmt.setString(1, supplier.getSupName());
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        Supplier newSupplier = (Supplier)newEntity;
        Supplier oldSupplier = (Supplier)oldEntity;
        stmt.setString(1, newSupplier.getSupName());
        stmt.setInt(2, oldSupplier.getSupplierId());
        stmt.setString(3, oldSupplier.getSupName());
        return stmt;
    }
}
