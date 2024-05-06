package com.keltenfalez.oreillyspring.controller;

import com.keltenfalez.oreillyspring.model.Invoice;
import com.keltenfalez.oreillyspring.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "api/v1/invoices")
@RestController
public class InvoiceController {

	private final InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@GetMapping
	public List<Invoice> getInvoices() {
		return invoiceService.getInvoices();
	}

	@GetMapping(path = "{customerId}")
	public List<Map<Long, String>> getInvoiceAndTenderTypeByCustomerId(@PathVariable("customerId") Long customerId) {
		return invoiceService.getInvoiceAndTenderTypeByCustomerId(customerId);
	}
}
