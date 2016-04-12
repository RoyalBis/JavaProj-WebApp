package com.travelexperts.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Package implements IEntity, Cloneable
{
    private int PackageId;
    private String PkgName;
    private Date PkgStartDate;
    private Date PkgEndDate;
    private String PkgDesc;
    private BigDecimal PkgBasePrice;
    private BigDecimal PkgAgencyCommission;
    private ArrayList<PackageProductSupplier> ProductSuppliers = new ArrayList<>();

    @Override
    public Package clone() throws CloneNotSupportedException {
        return (Package)super.clone();
    }

    public int getPackageId()
    {
        return PackageId;
    }

    public void setPackageId(int packageId)
    {
        PackageId = packageId;
    }

    public String getPkgName()
    {
        return PkgName;
    }

    public void setPkgName(String pkgName)
    {
        PkgName = pkgName;
    }

    public Date getPkgStartDate()
    {
        return PkgStartDate;
    }

    public void setPkgStartDate(Date pkgStartDate)
    {
        PkgStartDate = pkgStartDate;
    }

    public Date getPkgEndDate()
    {
        return PkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate)
    {
        PkgEndDate = pkgEndDate;
    }

    public String getPkgDesc()
    {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc)
    {
        PkgDesc = pkgDesc;
    }

    public BigDecimal getPkgBasePrice()
    {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(BigDecimal pkgBasePrice)
    {
        PkgBasePrice = pkgBasePrice;
    }

    public BigDecimal getPkgAgencyCommission()
    {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission)
    {
        PkgAgencyCommission = pkgAgencyCommission;
    }

    public ArrayList<PackageProductSupplier> getProductSuppliers()
    {
        return ProductSuppliers;
    }

    public void setProductSuppliers(ArrayList<PackageProductSupplier> productSuppliers)
    {
        ProductSuppliers = productSuppliers;
    }

    @Override
    public String toString()
    {
        return "Package{" +
                "PackageId=" + PackageId +
                ", PkgName='" + PkgName + '\'' +
                ", PkgStartDate=" + PkgStartDate +
                ", PkgEndDate=" + PkgEndDate +
                ", PkgDesc='" + PkgDesc + '\'' +
                ", PkgBasePrice=" + PkgBasePrice +
                ", PkgAgencyCommission=" + PkgAgencyCommission +
                ", ProductSuppliers=" + ProductSuppliers +
                '}';
    }

    @Override
    public int getId()
    {
        return getPackageId();
    }

    @Override
    public void setId(int id)
    {
        setPackageId(id);
    }
}
