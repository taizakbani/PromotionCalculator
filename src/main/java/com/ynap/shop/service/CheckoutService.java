package com.ynap.shop.service;

import static com.ynap.shop.dao.PromotionsDAO.activePromotionsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ynap.shop.model.Item;
import com.ynap.shop.model.Promotion;

public class CheckoutService {

    Map<String,Promotion> promotionRules=activePromotionsMap();

    private List<Item> itemsInBasket=new ArrayList<>();
    private List<Item> allItemsList= new ArrayList<>();

    public CheckoutService(Map<String,Promotion> promotionRules){
        this.promotionRules=promotionRules;
    }

    public void addToBasket(Item item) {
        if(nonNull(item)){
            if(!item.isEligibleForDiscount() || !allItemsList.contains(item)){
                getItemsInBasket().add(item);
            }
            else if(item.isEligibleForDiscount() && allItemsList.contains(item) && getPromotionRules().get("multipleQuantityItemLevelDiscount").isEnabled()){
                if(getItemsInBasket().indexOf(item)!=-1){
                    getItemsInBasket().remove(getItemsInBasket().indexOf(item));
                    getItemsInBasket().add(new Item(item.getId(),item.getName(),item.getDiscountedPrice()));
                }
                getItemsInBasket().add(new Item(item.getId(),item.getName(),item.getDiscountedPrice()));
            }
            else{
                getItemsInBasket().add(item);
            }
            allItemsList.add(item);
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total=valueOf(0);
        if(!getItemsInBasket().isEmpty()){
            for(Item item:getItemsInBasket()){
                total=total.add(item.getPrice());
            }
            Promotion promotion=getPromotionRules().get("orderLevelDiscount");
            if(promotion.isEnabled() && total.compareTo(promotion.getMinOrderTotalForDiscount())>0){
                total = total.subtract((total.multiply(valueOf((promotion.getDiscountInPercentage()/100.0)))));
            }
        }
        return total;
    }


    public List<Item> getItemsInBasket() {
        return itemsInBasket;
    }

    public void setItemsInBasket(List<Item> itemsList) {
        this.itemsInBasket = itemsList;
    }

    public Map<String, Promotion> getPromotionRules() {
        return promotionRules;
    }

}
