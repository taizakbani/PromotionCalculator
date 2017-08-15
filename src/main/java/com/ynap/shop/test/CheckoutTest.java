package com.ynap.shop.test;

import static com.ynap.shop.dao.PromotionsDAO.activePromotionsMap;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ynap.shop.model.Item;
import com.ynap.shop.model.Promotion;
import com.ynap.shop.service.CheckoutService;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutTest {
	
	@Mock
	Item nonDiscountedItem;
	
	@Mock
	Item nonDiscountedItem2;
	
	@Mock
	Item nonDiscountedItem3;
	
	@Mock
	Item highValueNonDiscountedItem;
	
	@Mock
	Item toteBagMock;
	
	@Mock
	Item beltMock;
	
	@Mock
	Item highValueDiscountedItem;
	
	@Mock
	Item discountedItem;
	
	@Mock
	Item discountedItem2;
	
	@Mock
	Item discountedItem3;
	
	@InjectMocks
	CheckoutService testObj;
	
	
	@Before
	public void init(){
		Map<String, Promotion> activePromotionsMap = activePromotionsMap();
		testObj=new CheckoutService(activePromotionsMap);
		
		when(nonDiscountedItem.getId()).thenReturn("1");
		when(nonDiscountedItem.getName()).thenReturn("nonDiscountedItem");
		when(nonDiscountedItem.getPrice()).thenReturn(valueOf(25.5));
		
		when(nonDiscountedItem2.getId()).thenReturn("2");
		when(nonDiscountedItem2.getName()).thenReturn("nonDiscountedItem");
		when(nonDiscountedItem2.getPrice()).thenReturn(valueOf(20.5));
		
		when(nonDiscountedItem3.getId()).thenReturn("3");
		when(nonDiscountedItem3.getName()).thenReturn("nonDiscountedItem");
		when(nonDiscountedItem3.getPrice()).thenReturn(valueOf(25.5));
		
		when(highValueNonDiscountedItem.getId()).thenReturn("4");
		when(highValueNonDiscountedItem.getName()).thenReturn("nonDiscountedItem");
		when(highValueNonDiscountedItem.getPrice()).thenReturn(valueOf(9500));
		
		when(toteBagMock.getId()).thenReturn("1011-005");
		when(toteBagMock.getName()).thenReturn("Tote Bag");
		when(toteBagMock.getPrice()).thenReturn(valueOf(8950));
		
		when(beltMock.getId()).thenReturn("2010-001");
		when(beltMock.getName()).thenReturn("Belt");
		when(beltMock.getPrice()).thenReturn(valueOf(990));
		
		
		when(highValueDiscountedItem.getId()).thenReturn("1");
		when(highValueDiscountedItem.getName()).thenReturn("discountedItem");
		when(highValueDiscountedItem.getPrice()).thenReturn(valueOf(9500));
		when(highValueDiscountedItem.isEligibleForDiscount()).thenReturn(true);
		when(highValueDiscountedItem.getDiscountedPrice()).thenReturn(valueOf(9000));
		
		when(discountedItem.getId()).thenReturn("2");
		when(discountedItem.getName()).thenReturn("keyChain");
		when(discountedItem.getPrice()).thenReturn(valueOf(95));
		when(discountedItem.isEligibleForDiscount()).thenReturn(true);
		when(discountedItem.getDiscountedPrice()).thenReturn(valueOf(90));
		
		when(discountedItem2.getId()).thenReturn("3");
		when(discountedItem2.getName()).thenReturn("keyChain2");
		when(discountedItem2.getPrice()).thenReturn(valueOf(95));
		when(discountedItem2.isEligibleForDiscount()).thenReturn(true);
		when(discountedItem2.getDiscountedPrice()).thenReturn(valueOf(90));
		
		when(discountedItem3.getId()).thenReturn("4");
		when(discountedItem3.getName()).thenReturn("keyChain3");
		when(discountedItem3.getPrice()).thenReturn(valueOf(95));
		when(discountedItem3.isEligibleForDiscount()).thenReturn(true);
		when(discountedItem3.getDiscountedPrice()).thenReturn(valueOf(90));
	}
	
	
	   @Test
	    public void testOrderTotalWhenToteBagBeltAndKeyChainInCart(){
	        testObj.addToBasket(toteBagMock);
	        testObj.addToBasket(beltMock);
	        testObj.addToBasket(discountedItem2);
	        BigDecimal total = testObj.getTotal();
	        assertTrue(total.compareTo(valueOf(9031.5))==0);
	    }
	    
	    @Test
	    public void testOrderTotalWhenBeltAndTwoKitchensInCart(){
	        testObj.addToBasket(beltMock);
	        testObj.addToBasket(discountedItem2);
	        testObj.addToBasket(discountedItem2);
	        BigDecimal total = testObj.getTotal();
	        assertTrue(total.compareTo(valueOf(1170))==0);
	    }
	    
	    
	    @Test
	    public void testOrderTotalWhenToteBagBeltAndTwoKeyChainsInCart(){
	        testObj.addToBasket(toteBagMock);
	        testObj.addToBasket(beltMock);
	        testObj.addToBasket(discountedItem2);
	        testObj.addToBasket(discountedItem2);
	        BigDecimal total = testObj.getTotal();
	        assertTrue(total.compareTo(valueOf(9108))==0);
	    }
	
	
	@Test
	public void testOrderTotalWhenTwoNonDiscountedItemsInCartAndOrderTotalBelowMinThreshold(){
		testObj.addToBasket(nonDiscountedItem);
		testObj.addToBasket(nonDiscountedItem2);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(46))==0);
	}
	
	
	@Test
	public void testOrderTotalWhenOneDiscountedItemInCartAndOrderTotalBelowMinThreshold(){
		testObj.addToBasket(discountedItem);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(95))==0);
	}
	
	@Test
	public void testOrderTotalWhenTwoSameDiscountedItemsInCartAndOrderTotalBelowMinThreshold(){
	    testObj.addToBasket(discountedItem);
	    testObj.addToBasket(discountedItem);
	    BigDecimal total = testObj.getTotal();
	    assertTrue(total.compareTo(valueOf(180))==0);
	}
	
	@Test
	public void testOrderTotalWhenThreeSameAndOneDifferentDiscountedItemsInCartAndOrderTotalBelowMinThreshold(){
	    testObj.addToBasket(discountedItem);
	    testObj.addToBasket(discountedItem);
	    testObj.addToBasket(discountedItem);
	    testObj.addToBasket(discountedItem2);
	    BigDecimal total = testObj.getTotal();
	    assertTrue(total.compareTo(valueOf(365))==0);
	}
	@Test
	public void testOrderTotalWhenThreeSameAndOneDifferentDiscountedItemAndOneUndiscountedItemInCartAndOrderTotalBelowMinThreshold(){
		testObj.addToBasket(discountedItem);
		testObj.addToBasket(discountedItem);
		testObj.addToBasket(discountedItem);
		testObj.addToBasket(discountedItem2);
		testObj.addToBasket(nonDiscountedItem);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(390.5))==0);
	}
	
	@Test
	public void testOrderTotalWhenOneDiscountedAndOneNonDiscountedItemsInCartAndOrderTotalBelowMinThreshold(){
		testObj.addToBasket(nonDiscountedItem);
		testObj.addToBasket(discountedItem2);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(120.5))==0);
	}
	
	
	@Test
	public void testOrderTotalWhenOneDiscountedAndTwoNonDiscountedItemsInCartAndOrderTotalBelowMinThreshold(){
	    testObj.addToBasket(nonDiscountedItem);
	    testObj.addToBasket(nonDiscountedItem2);
	    testObj.addToBasket(discountedItem2);
	    BigDecimal total = testObj.getTotal();
	    assertTrue(total.compareTo(valueOf(141))==0);
	}
	
	
	@Test
	public void testOrderTotalWhenTwoDifferentDiscountedAndTwoNonDiscountedItemsInCartAndOrderTotalBelowMinThreshold(){
		testObj.addToBasket(nonDiscountedItem);
		testObj.addToBasket(nonDiscountedItem3);
		testObj.addToBasket(discountedItem2);
		testObj.addToBasket(discountedItem);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(241))==0);
	}
	
	@Test
	public void testOrderTotalWhenTwoSameDiscountedAndTwoNonDiscountedItemsInCartAndOrderTotalBelowMinThreshold(){
		testObj.addToBasket(nonDiscountedItem);
		testObj.addToBasket(nonDiscountedItem3);
		testObj.addToBasket(discountedItem);
		testObj.addToBasket(discountedItem);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(231))==0);
	}
	

	@Test
	public void testOrderTotalWhenTotalAboveMinThreshold(){
	    testObj.addToBasket(highValueNonDiscountedItem);
	    BigDecimal total = testObj.getTotal();
	    assertTrue(total.compareTo(valueOf(8550))==0);
	}
	
	@Test
	public void testOrderTotalWhenTotalAboveMinThresholdWithTwoSameDiscountedItems(){
	    testObj.addToBasket(highValueDiscountedItem);
		testObj.addToBasket(highValueDiscountedItem);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(16200))==0);
	}
	
	@Test
	public void testOrderTotalWhenHighValueItemWithOtherItemsInCart(){
		testObj.addToBasket(highValueNonDiscountedItem);
		testObj.addToBasket(nonDiscountedItem);
		testObj.addToBasket(nonDiscountedItem3);
		testObj.addToBasket(discountedItem2);
		testObj.addToBasket(discountedItem);
		BigDecimal total = testObj.getTotal();
		assertTrue(total.compareTo(valueOf(8766.90))==0);
	}
	
	@Test
	public void testOrderTotalAboveThresholdButPromotionNotEnabled(){
	    testObj.addToBasket(highValueNonDiscountedItem);
	    testObj.addToBasket(nonDiscountedItem);
	    testObj.addToBasket(nonDiscountedItem3);
	    testObj.addToBasket(discountedItem);
	    testObj.addToBasket(discountedItem);
	    testObj.getPromotionRules().get("orderLevelDiscount").setEnabled(false);
	    BigDecimal total = testObj.getTotal();
	    assertFalse(total.compareTo(valueOf(8766.90))==0);
	    assertTrue(total.compareTo(valueOf(9731))==0);
	}
	
	@Test
	public void testOrderTotalAboveThresholdButItemPromotionNotEnabled(){
	    testObj.getPromotionRules().get("multipleQuantityItemLevelDiscount").setEnabled(false);
		testObj.addToBasket(highValueNonDiscountedItem);
		testObj.addToBasket(nonDiscountedItem);
		testObj.addToBasket(nonDiscountedItem2);
		testObj.addToBasket(nonDiscountedItem3);
		testObj.addToBasket(discountedItem);
		testObj.addToBasket(discountedItem);
		testObj.addToBasket(discountedItem);
		BigDecimal total = testObj.getTotal();
		assertFalse(total.compareTo(valueOf(8766.90))==0);
		assertTrue(total.compareTo(valueOf(8870.85))==0);
	}
	
	   @Test
	    public void testOrderTotalAboveThresholdButItemAndOrderPromotionNotEnabled(){
	        testObj.getPromotionRules().get("multipleQuantityItemLevelDiscount").setEnabled(false);
	        testObj.addToBasket(highValueNonDiscountedItem);
	        testObj.addToBasket(nonDiscountedItem);
	        testObj.addToBasket(nonDiscountedItem2);
	        testObj.addToBasket(nonDiscountedItem3);
	        testObj.addToBasket(discountedItem);
	        testObj.addToBasket(discountedItem);
	        testObj.addToBasket(discountedItem);
	        testObj.getPromotionRules().get("orderLevelDiscount").setEnabled(false);
	        BigDecimal total = testObj.getTotal();
	        assertFalse(total.compareTo(valueOf(8870.85))==0);
	        assertTrue(total.compareTo(valueOf(9856.5))==0);
	    }
	
	
}
