package com.keltenfalez.oreillyspring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.keltenfalez.oreillyspring.utils.HashMapConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Map;

@Entity
@Table(name  = "invoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

	@Id
	@Column(
			name = "invoice_id",
			updatable = false
	)
	private Long invoiceId;

	@Column(name = "customer_id")
	private Long customerId;

	/*
	Instead of saving these attributes in a separate table, I am going to store them as JSON in a column in the Invoice Table.
	I don't want a complex DB schema, and I want to have optimal performance!
	A standard JSON object will represent the tenderType attributes as a HashMap.
	 */
	@Column(name = "tender_details")
	@Convert(converter = HashMapConverter.class) // telling Hibernate to use the AttributeConverter for this tenderType field.
	private Map<String, Object> tenderType;

	@Column(name = "time")
	@JsonFormat(pattern="HH:mm")
	private LocalTime time;

	@Column(name = "store_number")
	private Long storeNumber;

	@Override
	public String toString() {
		return "Invoice{" +
				"invoice_id=" + invoiceId +
				", customer_id=" + customerId +
				", type=" + tenderType +
				", time=" + time +
				", storeNumber=" + storeNumber + '\'' +
				'}';
	}

}
