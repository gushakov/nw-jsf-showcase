package ch.unil.sflight.java.dto;

import java.io.Serializable;


/*
FLIGHTTIME	Types	S_FLTIME	INT4	10	0	Durée du vol
DISTANCE	Types	S_DISTANCE	QUAN	9	4	Distance
UNIT	Types	S_MUN	UNIT	3	0	Unité de mesure
UNIT_ISO	Types	S_MUN_ISO	CHAR	3	0	Unité de mesure en code ISO
PLANETYPE	Types	S_PLANETYE	CHAR	10	0	Type avion
FLIGHTTYPE	Types	S_FLTYPE	CHAR	1	0	Type vol
 */


/*
ADDITIONAL_INFO

       FLIGHTTIME                       6:01
       DISTANCE                       2,572.0000
       UNIT                           MI
       UNIT_ISO                       SMI
       PLANETYPE                      747-400
       FLIGHTTYPE
 */


@SuppressWarnings("serial")
public class AdditionalInfo implements Serializable {
	private int flightTime;
	private double distance;
	private String unit;
	private String planeType;
	public int getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	
	@Override
	public String toString() {
		return "AdditionalInfo [distance=" + distance + ", flightTime="
				+ flightTime + ", planeType=" + planeType + ", unit=" + unit
				+ "]";
	}
	
}
