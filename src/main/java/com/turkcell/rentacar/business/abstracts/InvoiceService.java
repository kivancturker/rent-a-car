package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.invoices.GetByIdInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoices.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoices.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoices.DateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoices.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

public interface InvoiceService {
	DataResult<List<ListInvoiceDto>> getAll();
	Result add(CreateInvoiceRequest createInvoiceRequest);
	DataResult<GetByIdInvoiceDto> getById(int id);
	Result update(int id, UpdateInvoiceRequest updateInvoiceRequest);
	Result delete(int id);
	DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(DateInvoiceRequest dateInvoiceRequest);
}
