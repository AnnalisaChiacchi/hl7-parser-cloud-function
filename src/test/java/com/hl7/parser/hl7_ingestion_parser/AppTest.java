package com.hl7.parser.hl7_ingestion_parser;

import com.google.gson.JsonObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	String oruMessage = "MSH|^~\\&|SQLAB||SMS||201601081056||ORU^R01|34|P|2.2|151760005618||||||^^^^^\r"
    			+ "PID|000|111|8505059^^^AMC^MR|CCC|ICDTENPONE^TWENTY^^^^^|DDD|19810519|M|EEE|FFF|GGG|HHH|||MMM|NNN|OOO|500084652^^^^^\r"
    			+ "ORC|RE|SO80773465-0|||||^^^201601081054|||||08443^PAUZE, DANIEL K|||||||\r"
    			+ "OBR||SO80773465-0||LABTYSCR^TYPE + SCREEN (CONVERT)^L^TYSCR^ - ^LN|||201601081054|||||||201601081054|^|08443^PAUZE, DANIEL K||||F16050||||BB|F|TYSCR^TYSCR|^^^^^R|^~^~||||||| - ||||||||\r"
    			+ "OBX|0|ST|%EXX^CROSSMATCH EXPIRATION^L^%EXX^^LN||01/12/2016||||||F|||||^^^^^^^^^^^|\r"
    			+ "OBX|0|ST|%ABR^ABO/RH(D)^L^882-1^ABO + Rh group Type in Blood^LN||O`POS||||||F|||||^^^^^^^^^^^|\r"
    			+ "OBX|0|ST|%AS^ANTIBODY SCREEN^L^890-4^Blood group antibody screen Presence in Serum or Plasma^LN||NEG||||||F|||||^^^^^^^^^^^|\r";
    			
    	JsonObject objIn = new JsonObject();
		
		objIn.addProperty("hl7input", oruMessage);
		
		System.out.println(objIn.toString());
		
		HL7Parser messageValidator = new HL7Parser();
		
		JsonObject objOut = new JsonObject();
		
		objOut = messageValidator.main(objIn);
		
        assertTrue( objOut.get("valid").getAsString().equals("true"));
        //assertTrue( objOut.get("metadata").get("messagetype").getAsString().notEmpty());
    }
}
