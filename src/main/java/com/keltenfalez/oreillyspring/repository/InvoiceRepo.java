package com.keltenfalez.oreillyspring.repository;

import com.keltenfalez.oreillyspring.model.Invoice;

import java.util.List;
import java.util.Map;

public interface InvoiceRepo {
	List<Invoice> getInvoices();

	List<Map<Long, String>> getInvoiceAndTenderTypeByCustomerId(Long customerId);
}
