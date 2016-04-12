package com.travelexperts.business;

/**
 * Created by 723403 on 3/15/2016.
 */
public class ProductSupplier implements IEntity
{
    private int ProductSupplierId;
    private Product product;
    private Supplier supplier;

    public int getProductSupplierId()
    {
        return ProductSupplierId;
    }

    public void setProductSupplierId(int productSupplierId)
    {
        ProductSupplierId = productSupplierId;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSupplier that = (ProductSupplier) o;

        if (ProductSupplierId != that.ProductSupplierId) return false;
        if (!product.equals(that.product)) return false;
        return supplier.equals(that.supplier);
    }

    @Override
    public int hashCode()
    {
        int result = ProductSupplierId;
        result = 31 * result + product.hashCode();
        result = 31 * result + supplier.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "{"      +
               product  +
                ", "    + 
               supplier +
                "}";
    }

    @Override
    public int getId()
    {
        return getProductSupplierId();
    }

    @Override
    public void setId(int id)
    {
        setProductSupplierId(id);
    }
}
