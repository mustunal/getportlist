package org.mycompany.dto;

import java.util.ArrayList;
import java.util.List;


public class GetPortListRestResponse {
	private String airlineCode;
	private List<Port> portList = new ArrayList<>();

	
	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public List<Port> getPortList() {
		return portList;
	}

	public void setPortList(List<Port> portList) {
		this.portList = portList;
	}
	
}
