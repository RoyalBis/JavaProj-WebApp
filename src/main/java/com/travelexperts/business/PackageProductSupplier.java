package com.travelexperts.business;
/**
 * Created by 723403 on 3/15/2016.
 */
public class PackageProductSupplier implements IEntity
{
    private int PackageId;
    private ProductSupplier productsupplier;

    public int getPackageId(){
        return PackageId;
    }

    public void setPackageId(int packageId){
        PackageId = packageId;
    }

    public ProductSupplier getProductsupplier(){
        return productsupplier;
    }

    public void setProductsupplier(ProductSupplier productsupplier) {
        this.productsupplier = productsupplier;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageProductSupplier that = (PackageProductSupplier) o;

        if (PackageId != that.PackageId) return false;
        return productsupplier.equals(that.productsupplier);
    }

    @Override
    public int hashCode()
    {
        int result = PackageId;
        result = 31 * result + productsupplier.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return  productsupplier.toString();
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
