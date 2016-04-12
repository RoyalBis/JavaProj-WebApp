package com.travelexperts.model;

import com.travelexperts.business.IEntity;
import com.travelexperts.business.Package;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class PackageProvider extends EntityProvider
{
    private static final IProvider provider = new PackageProvider();

    //TABLE
    public static final String table = "Packages";
    //All columns
    public static final String[] allColumns =
            {
                    "PackageId",
                    "PkgName",
                    "PkgStartDate",
                    "PkgEndDate",
                    "PkgDesc",
                    "PkgBasePrice",
                    "PkgAgencyCommission"
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
            allColumns[1] + ", " +
            allColumns[2] + ", " +
            allColumns[3] + ", " +
            allColumns[4] + ", " +
            allColumns[5] + ", " +
            allColumns[6] + ") " +
            "Values(" + repeat("?", allColumns.length - 1) + ")";

    private static final String update =
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
        entityList = provider.FetchAll(sql);
        return entityList;
    }

    public static Package GetById(int id)
    {
        String sql = getById;
        return (Package) provider.Fetch(sql, id);
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

    public static boolean Add(Package pkg)
    {
        String sql = insert;
        return provider.Insert(sql, pkg);
    }

    public static boolean Modify(Package newPkg, Package oldPkg)
    {
        String sql = update;
        return provider.Update(sql, newPkg, oldPkg);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        Package pkg = new Package();
        pkg.setPackageId(rs.getInt(i++));
        pkg.setPkgName(rs.getString(i++));
        pkg.setPkgStartDate(rs.getDate(i++));
        pkg.setPkgEndDate(rs.getDate(i++));
        pkg.setPkgDesc(rs.getString(i++));
        pkg.setPkgBasePrice(rs.getBigDecimal(i++));
        pkg.setPkgAgencyCommission(rs.getBigDecimal(i++));
        return pkg;
    }

    @Override
    public PreparedStatement PrepEntity(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        Package pkg = (Package)entity;
        stmt.setString(1, pkg.getPkgName());
        stmt.setDate(2, (Date) pkg.getPkgStartDate());
        stmt.setDate(3, (Date) pkg.getPkgEndDate());
        stmt.setString(4, pkg.getPkgDesc());
        stmt.setBigDecimal(5, pkg.getPkgBasePrice());
        stmt.setBigDecimal(6, pkg.getPkgAgencyCommission());
        pkg.getProductSuppliers().forEach(PkgProdSupProvider::Add);
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        Package newPkg = (Package)newEntity;
        Package oldPkg = (Package)oldEntity;
        stmt.setString(1, newPkg.getPkgName());
        stmt.setDate(2, (Date)newPkg.getPkgStartDate());
        stmt.setDate(3, (Date)newPkg.getPkgEndDate());
        stmt.setString(4, newPkg.getPkgDesc());
        stmt.setBigDecimal(5, newPkg.getPkgBasePrice());
        stmt.setBigDecimal(6, newPkg.getPkgAgencyCommission());
        stmt.setInt(7, oldPkg.getPackageId());
        stmt.setString(8, oldPkg.getPkgName());
        stmt.setDate(9, (Date)oldPkg.getPkgStartDate());
        stmt.setDate(10, (Date)oldPkg.getPkgEndDate());
        stmt.setString(11, oldPkg.getPkgDesc());
        stmt.setBigDecimal(12, oldPkg.getPkgBasePrice());
        stmt.setBigDecimal(13, oldPkg.getPkgAgencyCommission());

        //Loop through add each prodsup in the new pkg that is not in the old pkg
        newPkg.getProductSuppliers().stream()
                .filter(pkgProdSupp -> !oldPkg.getProductSuppliers()
                        .contains(pkgProdSupp))
                .forEach(PkgProdSupProvider::Add);

        //Loop through and remove each prodsub in olg pkg that is not in the new pkg
        oldPkg.getProductSuppliers().stream()
                .filter(pkgProdSupp -> !newPkg.getProductSuppliers()
                        .contains(pkgProdSupp))
                .forEach(PkgProdSupProvider::Remove);

        return stmt;
    }
}
