package org.mycompany;



import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.mycompany.dto.GetPortListRequest;
import org.mycompany.dto.GetPortListRestResponse;


public class RestApiRouteBuilder extends RouteBuilder {

	
	@Override
	public void configure() throws Exception {
		restConfiguration()
		.component("spark-rest")
		.port("8085")
		.bindingMode(RestBindingMode.json);
		
		
		rest("/api/").description("Hello API")
		.id("hello-api")
		.get("/sayHello")
		.produces("application/json")
		.enableCORS(true)
		.route().transform().simple("{ \"Message\": \"Hello New World\" }");
		
		rest("/api/").description("Get Port List API")
			.id("getPortList-api")
			.post("/getPortList")
			.produces("application/json")
	        .consumes("application/json")
	        .enableCORS(true)
	        .type(GetPortListRequest.class)
	        .outType(GetPortListRestResponse.class)
	        .to("direct:getPortListDirect");
	
	
		from("direct:getPortListDirect").routeId("getPortListDirect-route")
			.tracing()
			.log(">>> ${body.airlineCode}")
			.process(new GetPortListAPIProcessor())
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
	}
}
