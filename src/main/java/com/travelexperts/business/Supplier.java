package com.travelexperts.business;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Supplier implements IEntity
{
    private int SupplierId;
    private String SupName;

    public int getSupplierId()
    {
        return SupplierId;
    }

    public void setSupplierId(int supplierId)
    {
        SupplierId = supplierId;
    }

    public String getSupName() { return SupName; }

    public void setSupName(String supName)
    {
        SupName = supName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (SupplierId != supplier.SupplierId) return false;
        return SupName.equals(supplier.SupName);

    }

    @Override
    public int hashCode()
    {
        int result = SupplierId;
        result = 31 * result + SupName.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return SupName; 
    }

    @Override
    public int getId()
    {
        return getSupplierId();
    }

    @Override
    public void setId(int id)
    {
        setSupplierId(id);
    }
}
