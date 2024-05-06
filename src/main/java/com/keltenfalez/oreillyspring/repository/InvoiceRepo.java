package com.keltenfalez.oreillyspring.repository;

import com.keltenfalez.oreillyspring.model.Invoice;

import java.util.List;
import java.util.Map;

/*
This interface is used to provide flexibility in case later on we want to connect this project to a real DB.
Abstract methods enforce our implementation class to use them.
 */
public interface InvoiceRepo {
	List<Invoice> getInvoices();

	List<Map<Long, String>> getInvoiceAndTenderTypeByCustomerId(Long customerId);
}
