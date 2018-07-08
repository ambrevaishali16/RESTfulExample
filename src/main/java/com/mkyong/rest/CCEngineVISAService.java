package com.mkyong.rest;
 
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
 
@Path("/VISA")
public class CCEngineVISAService {
 
	@GET
	@Path("/{param}")
	@Produces ("text/html")
	public Response getMsg(@PathParam("param") String msg) {
 
		StringBuilder output = new StringBuilder();
		CreditCardEngine creditCardEngine = new CreditCardEngine();
		List<CredtCardNumber> ccNumberList = creditCardEngine.generateCCNumber("VISA", Integer.valueOf(msg));
		int count = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		output.append(msg + " CC Numbers of VISA and expiry date : <br>");
		for(CredtCardNumber credtCardNumber : ccNumberList) {
			output.append(count++);
			output.append(") CC Number : " + credtCardNumber.getCcNumber() + ",  Expiry date : " + sdf.format(credtCardNumber.getExpiaryDate()));
			output.append("<br>");
		}
 
		return Response.status(200).entity(output.toString()).build();
 
	}
 
}