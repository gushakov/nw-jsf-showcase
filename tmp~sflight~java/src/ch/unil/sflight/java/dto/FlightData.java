package ch.unil.sflight.java.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


/*
BAPISFLDAT
AIRLINEID	S_CARR_ID	CHAR	3	0	Désignation de la compagnie aérienne
AIRLINE	S_CARRNAME	CHAR	20	0	Nom d'une compagnie aérienne
CONNECTID	S_CONN_ID	NUMC	4	0	Code liaison aérienne individuelle
FLIGHTDATE	S_DATE	DATS	8	0	Date du vol
AIRPORTFR	S_FROMAIRP	CHAR	3	0	Aéroport de départ
CITYFROM	S_FROM_CIT	CHAR	20	0	Ville de départ
AIRPORTTO	S_TOAIRP	CHAR	3	0	Aéroport d'arrivée
CITYTO	S_TO_CITY	CHAR	20	0	Ville d'arrivée
DEPTIME	S_DEP_TIME	TIMS	6	0	Heure de départ
ARRTIME	S_ARR_TIME	TIMS	6	0	Heure d'arrivée
ARRDATE	S_ARRDATE	DATS	8	0	Date d'arrivée
PRICE	S_PRICE_2	DEC	23	4	Pris de liste standard du vol
CURR	S_CURRCODE	CUKY	5	0	Devise interne de la compagnie aérienne
CURR_ISO	S_CURR_ISO	CHAR	3	0	Code de devise en code ISO
*/

@SuppressWarnings("serial")
public class FlightData implements Serializable {
	private String airlineId;
	private String airlineName;
	private String connectId;
	private Date flightDate;
	private String airportFrom;
	private String cityFrom;
	private String airportTo;
	private String cityTo;
	private Time departureTime;
	private Time arrivalTime;
	private Date arrivalDate;
	private BigDecimal price;
	private String currency;
	
	public String getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getConnectId() {
		return connectId;
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
	public String getAirportFrom() {
		return airportFrom;
	}
	public void setAirportFrom(String airportFrom) {
		this.airportFrom = airportFrom;
	}
	public String getCityFrom() {
		return cityFrom;
	}
	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}
	public String getAirportTo() {
		return airportTo;
	}
	public void setAirportTo(String airportTo) {
		this.airportTo = airportTo;
	}
	public String getCityTo() {
		return cityTo;
	}
	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}
	public Time getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
