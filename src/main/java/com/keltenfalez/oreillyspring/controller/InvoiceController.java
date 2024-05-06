package com.keltenfalez.oreillyspring.controller;

import com.keltenfalez.oreillyspring.model.Invoice;
import com.keltenfalez.oreillyspring.service.InvoiceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "api/v1/invoices")
@RestController
@Slf4j
public class InvoiceController {

	private final InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@GetMapping
	@ApiOperation(
			value = "gets all invoices",
			notes = "Returns a List<Invoice>",
			response = List.class
	)
	public List<Invoice> getInvoices() {
		log.info("Attempting to fetch all invoices...");
		return invoiceService.getInvoices();
	}

	@GetMapping(path = "{customerId}")
	@ApiOperation(
			value = "gets all invoices and tendertypes for that customerID from the invoice table",
			notes = "returns a list of Maps<Long, Object",
			response = List.class
	)
	public List<Map<Long, String>> getInvoiceAndTenderTypeByCustomerId(@PathVariable("customerId") Long customerId) {
		log.info("Attempting to fetch all invoices and tender types...");
		return invoiceService.getInvoiceAndTenderTypeByCustomerId(customerId);
	}
}
