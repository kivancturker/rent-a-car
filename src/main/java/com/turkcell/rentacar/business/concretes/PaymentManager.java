package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.dtos.payments.GetByIdPaymentDto;
import com.turkcell.rentacar.business.dtos.payments.ListPaymentDto;
import com.turkcell.rentacar.business.exceptions.BusinessException;
import com.turkcell.rentacar.business.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.core.utils.constants.Messages;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.ErrorDataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService{

	PosService posService;
	ModelMapperService modelMapperService;
	PaymentDao paymentDao;
	InvoiceService invoiceService;
	
	@Autowired
	public PaymentManager(PosService posService, ModelMapperService modelMapperService, PaymentDao paymentDao,InvoiceService invoiceService) {
		this.posService = posService;
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
		this.invoiceService=invoiceService;
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		toSendPosService(createPaymentRequest);
	
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		checkIfInvoiceId(createPaymentRequest.getInvoiceNo());
				
		checkInvoiceIdExist(createPaymentRequest.getInvoiceNo());
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(Messages.PAYMENT_SUCCESS);
		
	}

	@Override
	public DataResult<List<ListPaymentDto>> getAll() {
		var result = this.paymentDao.findAll();
		
		List<ListPaymentDto> response = result.stream().map(payment -> this.modelMapperService.forDto()
				.map(payment, ListPaymentDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListPaymentDto>>(response);
	}

	@Override
	public DataResult<GetByIdPaymentDto> getById(int paymentId) {
		Payment result = this.paymentDao.getById(paymentId);
		if (result == null) {
			
			return new ErrorDataResult<GetByIdPaymentDto>(Messages.PAYMENT_ID_NOT_FOUND);
		}
		GetByIdPaymentDto response = this.modelMapperService.forDto().map(result, GetByIdPaymentDto.class);
		return new SuccessDataResult<GetByIdPaymentDto>(response);
	}
	
	private void toSendPosService(CreatePaymentRequest createPaymentRequest) {
		
		this.posService.payment(createPaymentRequest);
	}
	
	private boolean checkIfInvoiceId(int invoiceId) {
		
		if (this.paymentDao.getPaymentByInvoiceInvoiceNo(invoiceId) == null) {
		
			return true;
		}
		
		throw new BusinessException(Messages.PAYMENT_ALREADY_MADE);
	}
	
	private boolean checkInvoiceIdExist(int invoiceId) {
		
		if(this.invoiceService.getById(invoiceId) != null) {
		
			return true;
		}
		throw new BusinessException(Messages.PAYMENT_INVOICEID_NOT_FOUND);
	}
	
}