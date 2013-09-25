package ch.unil.sflight.java.dto;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class FlightBooking implements Serializable {
	private String airlineId;
	private String bookingNumber;
	private BigDecimal tiketPrice;
	private String currency;
	public String getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public BigDecimal getTiketPrice() {
		return tiketPrice;
	}
	public void setTiketPrice(BigDecimal tiketPrice) {
		this.tiketPrice = tiketPrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
