package com.travelexperts.business;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Product implements IEntity
{
    private int ProductId;
    private String ProdName;

    public int getProductId() { return ProductId; }

    public void setProductId(int productId)
    {
        ProductId = productId;
    }

    public String getProdName()
    {
        return ProdName;
    }

    public void setProdName(String prodName)
    {
        ProdName = prodName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (ProductId != product.ProductId) return false;
        return ProdName.equals(product.ProdName);

    }

    @Override
    public int hashCode()
    {
        int result = ProductId;
        result = 31 * result + ProdName.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return ProdName; 
    }

    @Override
    public int getId()
    {
        return getProductId();
    }

    @Override
    public void setId(int id)
    {
        setProductId(id);
    }
}
