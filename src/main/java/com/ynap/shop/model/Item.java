package com.ynap.shop.model;

import static java.lang.Boolean.valueOf;

import java.math.BigDecimal;

public class Item {
    private final String id;
    private final String name;
    private final BigDecimal price;
    private boolean isEligibleForDiscount;
    private BigDecimal discountedPrice;

    public Item(String id, String name, BigDecimal price) {
    	this.id = id;
    	this.name = name;
    	this.price = price;
    }
    
    public Item(String id, String name, BigDecimal price,boolean isEligibleForDiscount,BigDecimal discountedPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isEligibleForDiscount=isEligibleForDiscount;
        this.discountedPrice=discountedPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
    
    public boolean isEligibleForDiscount() {
		return isEligibleForDiscount;
	}

	public void setEligibleForDiscount(boolean isEligibleForDiscount) {
		this.isEligibleForDiscount = isEligibleForDiscount;
	}

	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!id.equals(item.id)) return false;
        if (!name.equals(item.name)) return false;
        if(isEligibleForDiscount!=valueOf(item.isEligibleForDiscount)) return false;
        if (!getDiscountedPrice().equals(item.getDiscountedPrice())) return false;
        return price.equals(item.price);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + discountedPrice.hashCode();
        result = 31 * result +valueOf(isEligibleForDiscount).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
