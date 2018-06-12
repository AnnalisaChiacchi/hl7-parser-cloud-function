package com.hl7.parser.hl7_ingestion_parser;

import com.google.gson.JsonObject;

public class MainHL7Validation {

	public static void main(String[] args) {
		
		String validMessage_new = "MSH|^~\\&|SQLAB||SMS||201601081056||ORU^R01|34|P|2.2|151760005618||||||^^^^^\r"
		+ "PID|000|111|8505059^^^AMC^MR|CCC|ICDTENPONE^TWENTY^^^^^|DDD|19810519|M|EEE|FFF|GGG|HHH|||MMM|NNN|OOO|500084652^^^^^\r"
		+ "ORC|RE|SO80773465-0|||||^^^201601081054|||||08443^PAUZE, DANIEL K|||||||\r"
		+ "OBR||SO80773465-0||LABTYSCR^TYPE + SCREEN (CONVERT)^L^TYSCR^ - ^LN|||201601081054|||||||201601081054|^|08443^PAUZE, DANIEL K||||F16050||||BB|F|TYSCR^TYSCR|^^^^^R|^~^~||||||| - ||||||||\r"
		+ "OBX|0|ST|%EXX^CROSSMATCH EXPIRATION^L^%EXX^^LN||01/12/2016||||||F|||||^^^^^^^^^^^|\r"
		+ "OBX|0|ST|%ABR^ABO/RH(D)^L^882-1^ABO + Rh group Type in Blood^LN||O`POS||||||F|||||^^^^^^^^^^^|\r"
		+ "OBX|0|ST|%AS^ANTIBODY SCREEN^L^890-4^Blood group antibody screen Presence in Serum or Plasma^LN||NEG||||||F|||||^^^^^^^^^^^|\r";
		
	/*	
		String adtMessage = "MSH|^~\\&|SOARIAN|E_AMC|||20151220101501||ADT^A10|154030586|T|2.4|||||||||\n"
		+ "ZSH|SIERX||||\n"
		+ "EVN|A10|20151220101501|||||\n"
		+ "PID|||8504352^^^^MR||DISCHARGE^ONE^^^^^L||19670324000000|F||D|90 STATE STREET^^EAST GREENBUSH^NY^12061^US^P^^38||^^^^1^465^6565666||ADA|C|NOV|500084629^^^^ECD\n"
		+ "PV1||E|EDWR^^EDWR-M65|||||||ED||||||N||ER||||||||||||||||||||||||||20151220101400||||||4388723|||\n"
		+ "ZPV|||||||||      ||||||||||||||||||||||||||||||      |      |      |||||||||||||||||^^^^^^^^^^|^^^^^^^^^^^^^^^^||^^UserDefinedString20^^Ambulatory~^^UserDefinedString22^^12/20/2015 10:14~^^UserDefinedString23^^12/20/2015 10:15|||\n"
		+ "ZP1||||||||||||||||||||||||||||||||||||^^UserDefinedString8^^E-Emancipated Youth Information Provided||||||||\n";
	*/	
		
		JsonObject objIn = new JsonObject();
		
		objIn.addProperty("hl7input", validMessage_new);
		
		//System.out.println(objIn.toString());
		
		HL7Validation messageValidator = new HL7Validation();
		
		JsonObject objOut = new JsonObject();
		
		objOut = messageValidator.validator(objIn);
		
		String outMessage = objOut.toString();
		
		//System.out.println(outMessage);
		
	}

}
