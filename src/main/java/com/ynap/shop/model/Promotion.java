package com.ynap.shop.model;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;

public class Promotion {
	
	private final String id;

	private String promotionType;
	
	private boolean isEnabled;
	
	private double discountInPercentage=0.0;
	
	private BigDecimal minOrderTotalForDiscount=valueOf(0);
	
	
	public Promotion(String id,String promotionType,boolean isEnabled){
		this.promotionType=promotionType;
		this.isEnabled=isEnabled;
		this.id=id;
	}
	public Promotion(String id,String promotionType,boolean isEnabled, double discountInPercentage,BigDecimal minOrderTotalForDiscount){
		this.promotionType=promotionType;
		this.isEnabled=isEnabled;
		this.discountInPercentage=discountInPercentage;
		this.id=id;
		this.minOrderTotalForDiscount=minOrderTotalForDiscount;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public String getId() {
		return id;
	}
	
	
	public double getDiscountInPercentage() {
		return discountInPercentage;
	}
	public void setDiscountInPercentage(double discountInPercentage) {
		this.discountInPercentage = discountInPercentage;
	}
	
	public BigDecimal getMinOrderTotalForDiscount() {
		return minOrderTotalForDiscount;
	}
	public void setMinOrderTotalForDiscount(BigDecimal minOrderTotalForDiscount) {
		this.minOrderTotalForDiscount = minOrderTotalForDiscount;
	}
	
}
