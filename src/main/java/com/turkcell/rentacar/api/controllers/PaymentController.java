package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.payments.GetByIdPaymentDto;
import com.turkcell.rentacar.business.dtos.payments.ListPaymentDto;
import com.turkcell.rentacar.business.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListPaymentDto>> getAll(){
		return paymentService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
		return paymentService.add(createPaymentRequest);
	}
	
	@GetMapping("/getid")
	public DataResult<GetByIdPaymentDto> getById(@RequestParam("paymentId") int paymentId){
		return paymentService.getById(paymentId);
	}
}
