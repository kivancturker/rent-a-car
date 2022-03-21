package com.turkcell.rentacar.business.outservices;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessResult;

@Service
public class FakeHalkBankManager {
	public Result odemeYap(int Cvv, String fullName, String cardNo) {
		System.out.println("Ödeme halk bankası ile yapılmıştır.");
		return new SuccessResult("Ödeme halk bankası ile yapılmıştır.");
	}
}
