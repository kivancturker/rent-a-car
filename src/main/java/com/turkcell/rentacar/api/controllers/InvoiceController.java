package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.invoices.GetByIdInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoices.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoices.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoices.DateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoices.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utils.results.DataResult;
import com.turkcell.rentacar.core.utils.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
	private InvoiceService invoiceService;

	@GetMapping
	public DataResult<List<ListInvoiceDto>> getAll() {
		return this.invoiceService.getAll();
	}

	@GetMapping(path = "{id}")
	public DataResult<GetByIdInvoiceDto> getById(@PathVariable(required = true, name = "id") int id) {
		return this.invoiceService.getById(id);
	}

	@PostMapping
	public Result add(@RequestBody CreateInvoiceRequest createProductRequest) {
		return this.invoiceService.add(createProductRequest);
	}

	@PutMapping(path = "{id}")
	public Result update(@PathVariable(required = true, name = "id") int id,
			@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.update(id, updateInvoiceRequest);
	}

	@DeleteMapping(path = "{id}")
	public Result delete(@PathVariable(required = true, name = "id") int id) {
		return this.invoiceService.delete(id);
	}
	
	/*@GetMapping(path="getByDate")
	public DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, LocalDate endDate) {
		return this.invoiceService.getAll();
	}*/
	
	@PostMapping("/getDate")
    public DataResult<List<ListInvoiceDto>> getAllBetweenTwoDates(@RequestBody DateInvoiceRequest dateInvoiceRequest) {
		return this.invoiceService.getAllBetweenTwoDates(dateInvoiceRequest);
    }
}
