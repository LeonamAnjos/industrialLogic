package com.industriallogic.orders;

class TestableOrder extends Order {
	public TestableOrder(String accountId) {
		super(accountId);
	}

	@Override
	protected String getOrderIdFromGenerator() {
		return accountId + ":10001";
	}

	@Override
	protected void reserveProduct(LineItem item) {
	}
}