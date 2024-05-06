package com.keltenfalez.oreillyspring.service;

import com.keltenfalez.oreillyspring.model.Invoice;
import com.keltenfalez.oreillyspring.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {
	private final InvoiceRepo invoiceRepo;

	@Autowired
	public InvoiceService(InvoiceRepo invoiceRepo) {
		this.invoiceRepo = invoiceRepo;
	}

	public List<Invoice> getInvoices() {
		return invoiceRepo.getInvoices();
	}

	public List<Map<Long, String>> getInvoiceAndTenderTypeByCustomerId(Long customerId) {
		return invoiceRepo.getInvoiceAndTenderTypeByCustomerId(customerId);
	}

}
