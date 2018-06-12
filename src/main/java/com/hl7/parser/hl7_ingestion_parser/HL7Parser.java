package com.hl7.parser.hl7_ingestion_parser;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import ca.uhn.hl7v2.validation.impl.ValidationContextFactory;

import com.google.gson.JsonObject;

/**
 * HL7 file validation using the provided ValidationContext implementations
 * ORU_R01 v2.2 MessageType implemented
 */
public class HL7Parser {

	public static JsonObject main(JsonObject obj) {

		JsonObject objOut= new JsonObject();
		JsonObject objMetadata = new JsonObject();
		
		if ( ! obj.isJsonNull()) {
			
			String hl7Message = obj.get("hl7input").getAsString();
			
			System.out.println("---BEGIN HL7 MSG ---");
			System.out.println(hl7Message);
			System.out.println("---END HL7 MSG ---");
	
			/*
			 * We will use a parser with default settings, as defined by the DefaultValidation class
			 */
			HapiContext context = new DefaultHapiContext();
			
			context.setValidationContext(ValidationContextFactory.defaultValidation());	
			
			PipeParser parser = context.getPipeParser();
		
			try {
				Message parsedhl7Message = parser.parse(hl7Message);
			
				System.out.println("---Parsed valid message---");
				System.out.println(parsedhl7Message);
				
				Terser terser = new Terser(parsedhl7Message);
				
				objOut.addProperty("valid", "true");
				//objMsh.addProperty("messagetype", terser.get("/.MSH-9-1") + "_" + terser.get("/.MSH-9-2"));
				
				objMetadata.addProperty("messagetype", terser.get("/.MSH-9-1") + "_" + terser.get("/.MSH-9-2"));
					
				String type = terser.get("/.MSH-9-1");
				
				if ( type.equals("ORU") || type.equals("ADT") ) {
					
					//objPid.addProperty("patientid", terser.get("/.PID-3-1"));
					objMetadata.addProperty("patientid", terser.get("/.PID-3-1"));
				
				if ( type.equals("ORU")) {
					
					//objOrc.addProperty("uniqueplacerid",terser.get("/.ORC-2-1"));
					objMetadata.addProperty("uniqueplacerid",terser.get("/.ORC-2-1"));
					//objObr.addProperty("universalserviceid",terser.get("/.OBR-4-1") + "_" + terser.get("/.OBR-4-2"));
					objMetadata.addProperty("universalserviceid",terser.get("/.OBR-4-1") + "_" + terser.get("/.OBR-4-2"));
					
				}
				
				//objObx.addProperty("observationidentifier",terser.get("/.OBX-3-1") + "_" + terser.get("/.OBX-3-2"));	
				objMetadata.addProperty("observationidentifier",terser.get("/.OBX-3-1") + "_" + terser.get("/.OBX-3-2"));
				}	
				objOut.add("metadata", objMetadata);
				
			} catch (HL7Exception e) {
				//objOut.addProperty("valid", "false");
				objOut.addProperty("error", e.getMessage());
				System.out.println("---Parsing Error---");
			}
			
			System.out.println("---BEGIN OUTPUT JSON---");
			System.out.println(objOut.toString());
			System.out.println("---END OUTPUT JSON---");
			
			}
		else {
			objOut.addProperty("error", "ERROR: The input json object is null");
			System.out.println("---ERROR: The input json object is null---");
		}
		
		return objOut;
	}

}
