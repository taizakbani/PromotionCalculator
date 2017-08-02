package com.ynap.shop.dao;

import static java.math.BigDecimal.valueOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ynap.shop.model.Promotion;

//data in this class should come from the database
public class PromotionsDAO {
	
protected static List<Promotion> promotionsList= new ArrayList<>();
protected static Map<String,Promotion> enabledPromotionsMap=new HashMap<>();
	
private PromotionsDAO(){
}
	
public static List<Promotion> getAllPromotions(){
	promotionsList.add(new Promotion("1","orderLevelDiscount",true,10.00,valueOf(9000)));
	promotionsList.add(new Promotion("2","multipleQuantityItemLevelDiscount",true));
	promotionsList.add(new Promotion("3","freeShipping",false));
	promotionsList.add(new Promotion("4","giftVoucher",false));
	return promotionsList;
}

public static Map<String, Promotion> activePromotionsMap(){
	List<Promotion> activePromotions= new ArrayList<>();
	for(Promotion promotion: getAllPromotions()){
		if(promotion.isEnabled()){
			activePromotions.add(promotion);
		}
	}
	for(Promotion promotion:activePromotions){
		enabledPromotionsMap.put(promotion.getPromotionType(),promotion);
	}
	return enabledPromotionsMap;
}


public static List<Promotion> getPromotionsList() {
	return promotionsList;
}

public static void setPromotionsList(List<Promotion> promotionsList) {
	PromotionsDAO.promotionsList = promotionsList;
}

public static Map<String, Promotion> getEnabledPromotionsMap() {
	return enabledPromotionsMap;
}

public static void setEnabledPromotionsMap(
		Map<String, Promotion> enabledPromotionsMap) {
	PromotionsDAO.enabledPromotionsMap = enabledPromotionsMap;
}

	
}
