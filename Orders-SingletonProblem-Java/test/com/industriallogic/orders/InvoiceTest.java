/// ***************************************************************************
/// Copyright (c) 2008, Industrial Logic, Inc., All Rights Reserved.
///
/// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
/// used by students during Industrial Logic's workshops or by individuals
/// who are being coached by Industrial Logic on a project.
///
/// This code may NOT be copied or used for any other purpose without the prior
/// written consent of Industrial Logic, Inc.
/// ****************************************************************************

package com.industriallogic.orders;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.industriallogic.util.Money;

public class InvoiceTest {

	class FakeExchangeRateService extends ExchangeRateService {

		private double rate;

		public FakeExchangeRateService(double rate) {
			this.rate = rate;
		}
		
		@Override
		public BigDecimal rateFor(String fromCurrency, String toCurrency) {
			return new BigDecimal(rate); 
		}
		
	}

	@Before
	public void setUpt() {
		ExchangeRateService.setInstance(new FakeExchangeRateService(2.5));
	}
	
	@Test
	public void currencyConversion() {
		Invoice invoice = new Invoice();
		Money netTotal = new Money(500);
		Money convertedTotal = invoice.convert(netTotal, "EUR");
		assertEquals(new Money(1250), convertedTotal);
	}

}
