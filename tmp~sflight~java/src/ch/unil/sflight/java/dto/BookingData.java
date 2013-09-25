package ch.unil.sflight.java.dto;

import java.io.Serializable;
import java.util.Date;

/*
AIRLINEID	Types	S_CARR_ID	CHAR	3	0	Désignation de la compagnie aérienne
BOOKINGID	Types	S_BOOK_ID	NUMC	8	0	Numéro de réservation
CONNECTID	Types	S_CONN_ID	NUMC	4	0	Code liaison aérienne individuelle
FLIGHTDATE	Types	S_DATE	DATS	8	0	Date du vol
CUSTOMERID	Types	S_CUSTOMER	NUMC	8	0	Numéro du passager
CLASS	Types	S_CLASS	CHAR	1	0	Classe vol
BOOKDATE	Types	S_BDATE	DATS	8	0	Date de réservation
COUNTER	Types	S_COUNTNUM	NUMC	8	0	Numéro du point de vente
AGENCYNUM	Types	S_AGNCYNUM	NUMC	8	0	Numéro de l'agence de voyages
RESERVED	Types	S_RESERV	CHAR	1	0	Code réservation
CANCELLED	Types	S_CANCEL	CHAR	1	0	Code d'annulation
PASSNAME	Types	S_PASSNAME	CHAR	25	0	Nom du passager
 */

@SuppressWarnings("serial")
public class BookingData implements Serializable {
	
	private String airlineId;
	private String bookingId;
	private String connectId;
	private Date flightDate;
	private String customerId;
	private Date bookDate;
	private boolean reserved;
	private boolean cancelled;
	private String passName;
	public String getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}
	public String getConnectId() {
		return connectId;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}
	public Date getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public boolean isReserved() {
		return reserved;
	}
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	public String getPassName() {
		return passName;
	}
	public void setPassName(String passName) {
		this.passName = passName;
	}

}
