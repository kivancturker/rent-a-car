package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.invoices.GetByIdInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoices.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoices.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoices.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utils.mappers.ModelMapperService;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;
import com.turkcell.rentacar.core.utils.results.SuccessDataResult;
import com.turkcell.rentacar.core.utils.results.SuccessResult;
import com.turkcell.rentacar.dataaccess.abstracts.InvoiceDao;
import com.turkcell.rentacar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {
	private final ModelMapperService modelMapperService;
	private final InvoiceDao invoiceDao;

	@Autowired
	public InvoiceManager(ModelMapperService modelMapperService, InvoiceDao invoiceDao) {
		this.modelMapperService = modelMapperService;
		this.invoiceDao = invoiceDao;
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAll() {
		var result = this.invoiceDao.findAll();
		List<ListInvoiceDto> response = result.stream()
				.map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult("Invoice.Add");
	}

	@Override
	public DataResult<GetByIdInvoiceDto> getById(int id) {
		var result = this.invoiceDao.getById(id);
		GetByIdInvoiceDto response = this.modelMapperService.forDto().map(result, GetByIdInvoiceDto.class);

		return new SuccessDataResult<GetByIdInvoiceDto>(response);
	}

	@Override
	public Result update(int id, UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		invoice.setInvoiceNo(id);

		this.invoiceDao.save(invoice);
		return new SuccessResult("Invoice.Update");
	}

	@Override
	public Result delete(int id) {
		this.invoiceDao.deleteById(id);
		return new SuccessResult("Invoice.Delete");
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(LocalDate fromDate, LocalDate toDate) {
		List<Invoice> invoices = this.invoiceDao.findByBillingDateBetween(fromDate, toDate);
		List<ListInvoiceDto> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}
}
