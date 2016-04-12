package com.travelexperts.model;

import com.travelexperts.business.IEntity;
import com.travelexperts.business.PackageProductSupplier;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/15/2016.
 */
public class PkgProdSupProvider extends EntityProvider
{
    public static IProvider provider = new PkgProdSupProvider();

    //TABLE
    public static final String table = "Packages_Products_Suppliers";
    //All columns
    public static final String[] allColumns =
            {
                "PackageId",
                "ProductSupplierId"
            };

    //SQL STATEMENTS
    private static final String getAll =
            "SELECT * " +
            "FROM " + table + " " +
            "ORDER BY " + PkgProdSupProvider.table + allColumns[0];

    private static final String getWhere =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " " +
            "WHERE ? = ?";

    private static final String insert =
            "INSERT INTO " + table + " " +
            "(" +
                       allColumns[0]  + ", " +
                       allColumns[1]  +
            "Values(" + repeat("?", allColumns.length) + ")";

    private static final String update =
            "UPDATE " + table + " " +
            "SET " +
                       allColumns[0] + " = ?, " +
                       allColumns[1] + " = ? "  +
            "WHERE " + allColumns[0] + " = ? " +
            "AND "   + allColumns[1] + " = ?";

    private static final String delete =
            "DELETE FROM " + table + " " +
            "WHERE " + allColumns[0] + " = ? " +
            "AND "   + allColumns[1] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        entityList = provider.FetchAll(sql);
        return entityList;
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

    public static boolean Add(PackageProductSupplier pkgProdSup)
    {
        String sql = insert;
        return provider.Insert(sql, pkgProdSup);
    }

    public static boolean Modify(PackageProductSupplier newPkgProdSup, PackageProductSupplier oldPkgProdSup)
    {
        String sql = update;
        return provider.Update(sql, newPkgProdSup, oldPkgProdSup);
    }

    public static boolean Remove(PackageProductSupplier PkgProdSup)
    {
        String sql = delete;
        return provider.Delete(sql, PkgProdSup);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        PackageProductSupplier pkgProdSup = new PackageProductSupplier();
        pkgProdSup.setPackageId(rs.getInt(1));
        pkgProdSup.setProductsupplier(ProductSupplierProvider.GetById(rs.getInt(2)));
        return pkgProdSup;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        int i = 1;
        PackageProductSupplier pkgProdSup = (PackageProductSupplier)entity;
        stmt.setInt(i++, pkgProdSup.getPackageId());
        stmt.setInt(i++, pkgProdSup.getProductsupplier().getProductSupplierId());
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        int i = 1;
        PackageProductSupplier newPkgProdSup = (PackageProductSupplier)newEntity;
        PackageProductSupplier oldPkgProdSup = (PackageProductSupplier)oldEntity;
        stmt.setInt(i++, newPkgProdSup.getPackageId());
        stmt.setInt(i++, newPkgProdSup.getProductsupplier().getProductSupplierId());
        stmt.setInt(i++, oldPkgProdSup.getPackageId());
        stmt.setInt(i++, oldPkgProdSup.getProductsupplier().getProductSupplierId());
        return stmt;
    }
}
