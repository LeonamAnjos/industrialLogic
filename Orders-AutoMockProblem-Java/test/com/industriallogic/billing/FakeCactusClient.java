package com.industriallogic.billing;

import com.industriallogic.util.Money;

public class FakeCactusClient implements CactusClient {

	@Override
	public boolean hasSufficientFundsFor(Money arg0) {
		return false;
	}

	@Override
	public void process(TransactionInfo arg0) {
	}

	@Override
	public void setHolderInfo(HolderInfo arg0) {
	}

	@Override
	public boolean startSession(String arg0) {
		return false;
	}

	@Override
	public void stopSession() {
	}

}