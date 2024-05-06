package com.keltenfalez.oreillyspring.repository;

import com.keltenfalez.oreillyspring.enums.TenderType;
import com.keltenfalez.oreillyspring.model.Invoice;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component(value = "fake")
public class InvoiceRepository implements InvoiceRepo {

	private List<Invoice> invoices; // ArrayList to simulate the Invoices table

	// this constructor creates dummy data to use since i'm not connecting to a real DB.
	public InvoiceRepository() {

		Map<String, Object> tenderDetailsMapOne = new HashMap<>();
		Map<String, Object> tenderDetailsMapTwo = new HashMap<>();
		Map<String, Object> tenderDetailsMapThree = new HashMap<>();

		tenderDetailsMapOne.put("amount", 23.43);
		tenderDetailsMapOne.put("type", TenderType.CASH.getTheType());
		tenderDetailsMapTwo.put("amount", 4.95);
		tenderDetailsMapTwo.put("type", TenderType.CASH.getTheType());
		tenderDetailsMapThree.put("amount", 100.12);
		tenderDetailsMapThree.put("type", TenderType.CREDIT.getTheType());

		this.invoices = Arrays.asList(
				new Invoice(54L, 1L, tenderDetailsMapOne, LocalTime.of(19,53), 999L),
				new Invoice(55L, 2L, tenderDetailsMapTwo,  LocalTime.of(12,00), 999L),
				new Invoice(56L, 2L, tenderDetailsMapThree,  LocalTime.of(8,49), 999L)
		);
	}

	@Override
	public List<Invoice> getInvoices() {
		return this.invoices;

	}

	@Override
	public List<Map<Long, String>> getInvoiceAndTenderTypeByCustomerId(Long customerId) {

		return invoices.stream()
				.filter(invoice -> invoice.getCustomerId().equals(customerId))
				.map(invoice -> {
					Map<Long, String> invoiceAndTenderTypeMap = new HashMap<>();
					invoiceAndTenderTypeMap.put(invoice.getInvoiceId(), invoice.getTenderType().get("type").toString());
					return invoiceAndTenderTypeMap;
				})
				.collect(Collectors.toList());
	}

}