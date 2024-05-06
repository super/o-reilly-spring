package com.keltenfalez.oreillyspring.service;

import com.keltenfalez.oreillyspring.enums.TenderType;
import com.keltenfalez.oreillyspring.model.Invoice;
import com.keltenfalez.oreillyspring.repository.InvoiceRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;

public class InvoiceServiceTest {

	@Mock
	private InvoiceRepo invoiceRepo;

	private InvoiceService underTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new InvoiceService(invoiceRepo);
	}

	@Test
	void itShouldGetAllInvoices() {

		// create dummy data...
		Map<String, Object> tenderDetailsMapOne = new HashMap<>();
		Map<String, Object> tenderDetailsMapTwo = new HashMap<>();
		Map<String, Object> tenderDetailsMapThree = new HashMap<>();

		tenderDetailsMapOne.put("amount", 23.43);
		tenderDetailsMapOne.put("type", TenderType.CASH.getTheType());
		tenderDetailsMapTwo.put("amount", 4.95);
		tenderDetailsMapTwo.put("type", TenderType.CASH.getTheType());
		tenderDetailsMapThree.put("amount", 100.12);
		tenderDetailsMapThree.put("type", TenderType.CREDIT.getTheType());

		List<Invoice> invoiceList = Arrays.asList(
				new Invoice(54L, 1L, tenderDetailsMapOne, LocalTime.of(19, 53), 999L),
				new Invoice(55L, 2L, tenderDetailsMapTwo, LocalTime.of(12, 00), 999L),
				new Invoice(56L, 2L, tenderDetailsMapThree, LocalTime.of(8, 49), 999L)
		);
		// given a valid request
		given(invoiceRepo.getInvoices())
				.willReturn(invoiceList);

		// when
		List<Invoice> underTestInvoices = underTest.getInvoices();

		Assertions.assertNotEquals(Collections.EMPTY_LIST, underTestInvoices);
	}

	@Test
	void itShouldGetAllInvoiceIdsAndTenderTypesByCustomerId() {

		// create dummy data...

		Long customerId = 2L;

		Map<String, Object> tenderDetailsMapOne = new HashMap<>();
		Map<String, Object> tenderDetailsMapTwo = new HashMap<>();
		Map<String, Object> tenderDetailsMapThree = new HashMap<>();

		tenderDetailsMapOne.put("amount", 23.43);
		tenderDetailsMapOne.put("type", TenderType.CASH.getTheType());
		tenderDetailsMapTwo.put("amount", 4.95);
		tenderDetailsMapTwo.put("type", TenderType.CASH.getTheType());
		tenderDetailsMapThree.put("amount", 100.12);
		tenderDetailsMapThree.put("type", TenderType.CREDIT.getTheType());

		List<Invoice> invoiceList = Arrays.asList(
				new Invoice(54L, 1L, tenderDetailsMapOne, LocalTime.of(19, 53), 999L),
				new Invoice(55L, 2L, tenderDetailsMapTwo, LocalTime.of(12, 00), 999L),
				new Invoice(56L, 2L, tenderDetailsMapThree, LocalTime.of(8, 49), 999L)
		);

		List<Map<Long, String>> invoiceIdsAndTenderTypesByCustomerIdMap = invoiceList.stream()
				.filter(invoice -> invoice.getCustomerId().equals(customerId))
				.map(invoice -> {
					Map<Long, String> invoiceAndTenderTypeMap = new HashMap<>();
					invoiceAndTenderTypeMap.put(invoice.getInvoiceId(), invoice.getTenderType().get("type").toString());
					return invoiceAndTenderTypeMap;
				})
				.collect(Collectors.toList());
		// given a valid request
		given(invoiceRepo.getInvoiceAndTenderTypeByCustomerId(customerId))
				.willReturn(invoiceIdsAndTenderTypesByCustomerIdMap);

		// when
		List<Map<Long, String>> invoiceAndTenderTypeByCustomerIdList = underTest.getInvoiceAndTenderTypeByCustomerId(customerId);

		Assertions.assertEquals(2, invoiceAndTenderTypeByCustomerIdList.size());
	}
}
