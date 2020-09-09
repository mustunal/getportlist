package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mycompany.dto.GetPortListRequest;
import org.mycompany.dto.GetPortListRestResponse;
import org.mycompany.dto.Port;

public class GetPortListAPIProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		GetPortListRequest getPortListRequest = (GetPortListRequest)exchange.getIn().getBody();
		
		System.out.println("Processor" + getPortListRequest.getAirlineCode());
		
		GetPortListRestResponse getPortListResponse = new GetPortListRestResponse();
		getPortListResponse.setAirlineCode(getPortListRequest.getAirlineCode());
		
		getPortListResponse.getPortList().add(new Port("IST"));
		getPortListResponse.getPortList().add(new Port("ESB"));
		getPortListResponse.getPortList().add(new Port("ADB"));
		
		exchange.getIn().setBody(getPortListResponse);
	}
}
