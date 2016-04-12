package com.travelexperts.model;

import com.travelexperts.business.IEntity;
import com.travelexperts.business.Product;
import com.travelexperts.business.ProductSupplier;
import com.travelexperts.model.EntityProvider;
import com.travelexperts.model.IProvider;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/15/2016.
 */
public class ProductSupplierProvider extends EntityProvider
{
    public static IProvider provider = new ProductSupplierProvider();

    //TABLE
    public static final String table = "Products_Suppliers";
    //All columns
    public static final String[] allColumns =
            {
                "ProductSupplierId",
                "ProductId",
                "SupplierId"
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
                    allColumns[1]  + ", " +
                    allColumns[2]  +
            ") " +
            "Values(" + repeat("?", allColumns.length - 1) + ")";

    private static final String update =
            "UPDATE " + table + " " +
            "SET " +
                       allColumns[1] + " = ?, " +
                       allColumns[2] + " = ? "  +
            "WHERE " + allColumns[0] + " = ? " +
            "AND "   + allColumns[1]  + " = ? " +
            "AND "   + allColumns[2] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        entityList = provider.FetchAll(sql);
        return entityList;
    }

    public static ProductSupplier GetById(int id)
    {
        String sql = getById;
        return (ProductSupplier) provider.Fetch(sql, id);
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

    public static boolean Add(ProductSupplier prodsupp)
{
    String sql = insert;
    return provider.Insert(sql, prodsupp);
}

    public static boolean Modify(Product newProdSupp, Product oldProdSupp)
    {
        String sql = update;
        return provider.Update(sql, newProdSupp, oldProdSupp);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        ProductSupplier prodsupp = new ProductSupplier();
        prodsupp.setProductSupplierId(rs.getInt(1));
        prodsupp.setProduct(ProductProvider.GetById(rs.getInt(2)));
        prodsupp.setSupplier(SupplierProvider.GetById(rs.getInt(3)));
        return prodsupp;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        ProductSupplier pkg = (ProductSupplier)entity;
        stmt.setInt(1, pkg.getProduct().getProductId());
        stmt.setInt(2, pkg.getSupplier().getSupplierId());
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        ProductSupplier newProdSupp = (ProductSupplier)newEntity;
        ProductSupplier oldProdSupp = (ProductSupplier)oldEntity;
        stmt.setInt(1, newProdSupp.getProduct().getProductId());
        stmt.setInt(2, newProdSupp.getSupplier().getSupplierId());
        stmt.setInt(3, oldProdSupp.getProductSupplierId());
        stmt.setInt(4, oldProdSupp.getProduct().getProductId());
        stmt.setInt(5, oldProdSupp.getSupplier().getSupplierId());
        return stmt;
    }
}
