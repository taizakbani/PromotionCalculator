Test Instructions
=================

Net-a-porter.com is an online luxury fashion retailer.
Below are a sample of items available on our site:

| Product Code | Name     | Price   |
| ------------ | -------- | ------: |
| 1011-005     | Tote Bag | £8,950  |
| 2010-001     | Belt     | £990    |
| 3000-002     | Keychain | £95     |

Our marketing team want to offer promotions based on the following criteria:

    1. if you spend over £9,000 then you get 10% off your purchase
    2. If you buy 2 or more keychains then the price drops to £90 per piece

Our checkout can scan items in any order and because our promotions will change,
it needs to be flexible regarding our promotional rules.

    The interface to our checkout looks like this (shown in Java):
    Checkout checkout = new Checkout(promotionalRules);
    checkout.addToBasket(item);
    checkout.addToBasket(item);

	Double price = checkout.total();

Implement a checkout system that fulfils these requirements.  It is not necessary to 
create a main() method or output.  The task is simply to implement the provided Checkout class.

Test Scenarios
--------------
| Basket | Total Price |
| :------ | ----------: |
|1011-005, 2010-001, 3000-002 | £9,031.50 |
| 3000-002, 2010-001, 3000-002 | £1,170.00 |
| 1011-005, 3000-002, 2010-001, 3000-002 | £9,108.00 |