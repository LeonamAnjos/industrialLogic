package com.industriallogic.orders;

public class TestableOrder extends Order {

	public TestableOrder(String accountId) {
		super(accountId);
	}
	
	@Override
	protected String generateOrderId() {
		return "newID";
	}

	@Override
	protected void reserveProduct(LineItem item) {
	}
}
