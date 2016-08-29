package com.muSWS.core;

import com.muSWS.deployment.DeployWSDLWebService;
import java.io.*;

import java.util.*;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.wsmo.common.exception.InvalidModelException;
import org.wsmo.wsml.ParserException;

/*
 * Performs the overall mutation based evaluation, as shown in figure.
 * Uses all the classes.
 */
public class MuSWSEvaluation {		
	
	public static String originalSWS=null;
	//public static String originalFileName = "com\\hello2\\HelloService2Skeleton.java";	
		

	public static String testInput1;
	public static String testInput2;
	public static String testInput3;
	public static String testInput4;
	public static String testInput5;
	public static String testInput6;
	public static String testInput7;
	public static String testInput8;
	public static String testInput9;
	public static String testInput10;
	public static String testInput11;
	public static String testInput12;
	public static String testInput13;
	public static String testInput14;
	public static String testInput15;
	public static String testInput16;
	public static String testInput17;
	public static String testInput18;
	public static String testInput19;
	public static String testInput20;
	public static String testInput21;
	public static String testInput22;
	public static String testInput23;
	public static String testInput24;
	public static String testInput25;
	public static String testInput26;
	
	
	public static TestCase[] testSuite= new TestCase[26];
	public static Hashtable <File, String[]> filesAndLinesRemoved= new Hashtable<File,String[]>();
	public static Hashtable <Mutant, Set<TestCase>> mutantsTestCasesHT = new Hashtable<Mutant, Set<TestCase>>();
	public static Mutant[] mutants = generateMutantsInfo();
	
	static{		
		testInput1= "wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase1 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer1\t " +
		"disc#itm2 hasValue disc#item1" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 200" ;
		
		testInput2="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase2 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer2\t " +
		"disc#itm2 hasValue disc#item2" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 200" ;
		
		
		testInput3="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase3 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer3\t " +
		"disc#itm2 hasValue disc#item3" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 200" ;
		
		
		testInput4="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase4 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer4\t " +
		"disc#itm2 hasValue disc#item4" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 99" ;
		
		
		testInput5="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase5 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer5\t " +
		"disc#itm2 hasValue disc#item5" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 100" ;
		
		
		testInput6="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase6 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer6\t " +
		"disc#itm2 hasValue disc#item6" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 101" ;
		
		
		testInput7="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase7 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer7\t " +
		"disc#itm2 hasValue disc#item7" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 200" ;
					
		testInput8="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase8 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer8\t " +
		"disc#itm2 hasValue disc#item8" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 350" ;
		
		testInput9="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase9 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer9\t " +
		"disc#itm2 hasValue disc#item9" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 350" ;
		
		testInput10="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase10 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer10\t " +
		"disc#itm2 hasValue disc#item10" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 350" ;
		
		testInput11="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase11 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer11\t " +
		"disc#itm2 hasValue disc#item11" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 350" ;
		
		testInput12="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase12 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer12\t " +
		"disc#itm2 hasValue disc#item12" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 499" ;
		
		testInput13="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase13 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer13\t " +
		"disc#itm2 hasValue disc#item13" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 500" ;
		
		testInput14="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase14 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer14t " +
		"disc#itm2 hasValue disc#item14" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 501" ;
		
		testInput15="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase15 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer15\t " +
		"disc#itm2 hasValue disc#item15" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 650" ;
		
		testInput16="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase16 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer16\t " +
		"disc#itm2 hasValue disc#item16" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 650" ;
		
		testInput17="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase17 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer17\t " +
		"disc#itm2 hasValue disc#item17" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 650" ;
		
		testInput18="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase18 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer18\t " +
		"disc#itm2 hasValue disc#item18" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 650" ;
		
		testInput19="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase19 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer19\t " +
		"disc#itm2 hasValue disc#item19" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 499" ;
		
		testInput20="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase20 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer20\t " +
		"disc#itm2 hasValue disc#item20" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 500" ;
		
		testInput21="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase21 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer21\t " +
		"disc#itm2 hasValue disc#item21" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 501" ;
		
		testInput22="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase22 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer22\t " +
		"disc#itm2 hasValue disc#item22" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 350" ;
		
		testInput23="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase23 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer23\t " +
		"disc#itm2 hasValue disc#item23" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 650" ;
		
		// test cases for ccType 
		
		testInput24="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase24 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer24\t " +
		"disc#itm2 hasValue disc#item24" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 600" ;
		
		testInput25="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase25 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer25\t " +
		"disc#itm2 hasValue disc#item25" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 600" ;
		

		testInput26="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase26 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer26\t " +
		"disc#itm2 hasValue disc#item26" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 600" ;		
		
		testSuite[0]= new TestCase(1, testInput1, "Valid Test Case!"); //Male ,18
		testSuite[1]= new TestCase(2, testInput2, "Valid Test Case"); // Male, 17 
		testSuite[2]= new TestCase(3, testInput3, "Valid Test Case"); // Female, 19
		testSuite[3]= new TestCase(4, testInput4, "Valid Test Case"); // ABC, 19
		testSuite[4]= new TestCase(5, testInput5, "Valid Test Case"); // ABC, 19
		testSuite[5]= new TestCase(6, testInput6, "Valid Test Case"); // ABC, 19
		testSuite[6]= new TestCase(7, testInput7, "Valid Test Case"); // ABC, 19
		testSuite[7]= new TestCase(8, testInput8, "Valid Test Case"); // ABC, 19		
		testSuite[8]= new TestCase(9, testInput9, "Valid Test Case"); // ABC, 19
		testSuite[9]= new TestCase(10, testInput10, "Valid Test Case"); // ABC, 19
		testSuite[10]= new TestCase(11, testInput11, "Valid Test Case"); // ABC, 19
		testSuite[11]= new TestCase(12, testInput12, "Valid Test Case"); // ABC, 19
		testSuite[12]= new TestCase(13, testInput13, "Valid Test Case"); // ABC, 19
		testSuite[13]= new TestCase(14, testInput14, "Valid Test Case"); // ABC, 19
		testSuite[14]= new TestCase(15, testInput15, "Valid Test Case"); // ABC, 19
		testSuite[15]= new TestCase(16, testInput16, "Valid Test Case"); // ABC, 19
		testSuite[16]= new TestCase(17, testInput17, "Valid Test Case"); // ABC, 19
		testSuite[17]= new TestCase(18, testInput18, "Valid Test Case"); // ABC, 19
		testSuite[18]= new TestCase(19, testInput19, "Valid Test Case"); // ABC, 19
		testSuite[19]= new TestCase(20, testInput20, "Valid Test Case"); // ABC, 19
		testSuite[20]= new TestCase(21, testInput21, "Valid Test Case"); // ABC, 19	
		testSuite[21]= new TestCase(22, testInput22, "Valid Test Case"); // ABC, 19
		testSuite[22]= new TestCase(23, testInput23, "Valid Test Case"); // ABC, 19
		testSuite[23]= new TestCase(24, testInput24, "Valid Test Case"); // ABC, 19
		testSuite[24]= new TestCase(25, testInput25, "Valid Test Case"); // ABC, 19
		testSuite[25]= new TestCase(26, testInput26, "Valid Test Case"); // ABC, 19		
	}
	
	// needs the complete SWS Identifier: 
	public static int[][] executeMutants(String originalSWSId, TestCase[] testSuite ) throws Exception{
		int[][] resultsTable =null;
		long t1= System.nanoTime();
		
		System.out.println("Replacing the old file with the original");
		//need to change this line with the new code:
		DeployWSDLWebService.replaceFileWith(new File("C:\\AXIS2_WebServices\\DiscountExample\\generated3\\src\\com\\discount\\DiscountWebServiceSkeleton.java"), new File("C:\\AXIS2_WebServices\\DiscountExample\\DiscountWebServiceSkeleton.java"));
		originalSWS=originalSWSId;
		System.out.println("Uploading the original Web service....");		
		DeployWSDLWebService.generateDeployableWebService();		
		DeployWSDLWebService.uploadWebService(DeployWSDLWebService.getRequiredFile(".aar", DeployWSDLWebService.WSWorkDIR_file).get(0).getName());
		System.out.println("Stopping main thread...");
		Thread.sleep(7000);
		System.out.println("resuming main thread...");
		//System.out.println("Refershing the Tomcat's services folder");
		File originalAAR= new File("C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF\\services\\DiscountWebService.aar"); //This is just to refersh the file explorer:		
		System.out.println("Executing Test Suite on original Web service....");
		String[] originalOutputs=null;
		originalOutputs = executeTestSuite(originalSWS, testSuite); //Step 1: Execute test cases on original SWS.
		System.out.println("Executed..!");
		System.out.println("Deleting the original \".aar\" file from Tomcat server");
		if(originalAAR.delete())
			System.out.println("Deleted.");		
		
		System.out.println("Printing the original Results");
		for(int i=0;i<originalOutputs.length;i++)
			System.out.println("originalOutputs["+i+"]:\t"+TestOracle.getValueFromOutput(originalOutputs[i]));
		
		File workDirectory= DeployWSDLWebService.WSWorkDIR_file; //Work directory, where original WS is being build
		File originalFile=null; // original java files to be given for the mutants generation: This should be from the Work directory, not from mutant Dir
		//MutantsGenerator mGen=new MutantsGenerator();
		File wsmlSWS = MutantsGenerator.getOriginalSWSFile(originalSWSId); //step 2: Get the file of the original SWS (HelloWS2.wsml) from the SWS Id: Implemented. get SWS File:		
		String gString= MutantsGenerator.getDynamicBindingGroundingStr(wsmlSWS); //Step 3: Get the grounding string from the dynamic binder: Implemented.		
		String SAWSDLFileName = MutantsGenerator.getSAWSDLFileNameFromGrounding(gString); //Step 4: Get the SAWSDL File from "grounding.properties" file: e.g.,  "hello.sawsdl" in groundings folder		
		File sawsdlFile=null;
		try {
			sawsdlFile=DeployWSDLWebService.getRequiredFile(SAWSDLFileName, MutantsGenerator.groundingLOC).get(0); //get SAWSDL file from goundings.properties.
			String wsName= MutantsGenerator.getWebServiceName(sawsdlFile); //Step 5: Get the web service name by reading the "SAWSDL" file:			
			String originalFileName=wsName+"Skeleton.java";	
			originalFile= DeployWSDLWebService.getRequiredFile(originalFileName, workDirectory).get(0); //Step 6: Get original file name from the work directory:		
			System.out.println("Making files suitable for MuJava Tool..."); 
			filesAndLinesRemoved= MutantsGenerator.makeFilesSuitableForMuJavaTool(originalFile);//Step 7: Make the files suitable to generate Mutants and before copying.			
			System.out.println("Generating mutants and then Mutant SWSs...");
			File[] mutantSWSs= MutantsGenerator.generateMutantSWSs(originalFile, originalSWSId);//Step 8: Generates Mutants programs and mutant SWSs for original program.
			System.out.println("Generated!");
			
			//Step 9: Create a Thread "t1" and start the WSMX server using this thread:
			/*Thread t1= new Thread(new Runnable(){
			public void run(){
				startWSMXServer();				
			}
			});
			System.out.println("Starting WSMX in a new thread....");
			t1.start();				
			//for (int i=0; i<100000;i++);		
			System.out.println("WSMX Started...");
			*/
			//Step 8: Insert back the lines that were removed while generating the mutants.
			System.out.println("Making files suitable for Web Service execution...");
			Enumeration<File> filesFromWhichLinesWereRemoved= filesAndLinesRemoved.keys();
			while(filesFromWhichLinesWereRemoved.hasMoreElements()){
				File currentFile= filesFromWhichLinesWereRemoved.nextElement();
				String[] linesRemovedFromCurrentFile=filesAndLinesRemoved.get(currentFile);
				MutantsGenerator.InsertOriginalLineBackToFile(currentFile, linesRemovedFromCurrentFile);// files are correct now and the .aar file will be correct.		
			}			
			//Step 9: Execute all the test cases from the Test suite one by one on the original SWS and mutant SWS 
			//Step 10: Compare the values. If output is equal--> fault not detected. If not equal fault detected.
			//Step 11: Store the result into the two-dimensional array. If fault detected store "1", otherwise "0"
			//String[] originalOutputs = new String[testSuite.length];			
						
			resultsTable= new int[mutantSWSs.length][testSuite.length];  //A two dimensional array that store the comparison values of the mutants and TestCases:
			
			//Hashtable <Mutant, Set<TestCase>> mutantsTestCases = new Hashtable<Mutant, Set<TestCase>>();
			//Mutant[] mutants = generateMutantsInfo();
			for (int i=0;i<5; i++){
				//boolean killed=false;				
				String[] mutantOutputs=null;				
				System.out.println("Picking Mutant\t"+mutants[i].getMutantName());
				String mutantSWSId = makeSWSFromMutant(mutants[i]); //creates .aar, uploads on Tomcat, makes the WSML file and creates SWS Identifier				
				System.out.println("Stopping main thread...");
				Thread.sleep(6000);
				System.out.println("Resuming main thread...");
				System.out.println("Executing SWS\t"+mutantSWSId);
				//Set<TestCase> testCasesKillingThisMutant= new HashSet<TestCase>();				
				for(int j=0; j<testSuite.length; j++ ){ //execute every test case
					
					mutantOutputs= new String[testSuite.length];
					String testInput=null;
					testInput= testSuite[j].getTestInput();
					TestCaseExecuter tcExec= null;
					tcExec= new TestCaseExecuter();										
					mutantOutputs[j] = tcExec.executeTestCase(mutantSWSId, testInput);
					System.out.println("Mutant \t"+mutants[i].getMutantId()+"\tTest Case\t"+testSuite[j].getTestId());
					
					String originalValue=null;
					originalValue=TestOracle.getValueFromOutput(originalOutputs[j]);
					String muValue=null;
					muValue=TestOracle.getValueFromOutput(mutantOutputs[j]);
					System.out.println("Original Output:\t"+originalValue+"\tMutant Output:\t"+muValue);
					boolean sameOutput=false;
					//sameOutputs= TestOracle.valueComparison(originalOutputs[j], mutantOutputs[j]);
					sameOutput= originalValue.equals(muValue);
					System.out.println("Output Same\t"+sameOutput);
					if(sameOutput)
						resultsTable[i][j]=0;
					else if(!sameOutput){
						resultsTable[i][j]=1;
						//testCasesKillingThisMutant.add(testSuite[j]);					
					}
				}
				//mutantsTestCases.put(mutants[i], testCasesKillingThisMutant);				
			}
			long t2=System.nanoTime();
			long timeTake=t2-t1;
			double timeInSeconds=timeTake/Math.pow(10.00, 9.0);
			System.out.println("Time taken is\t"+timeInSeconds+" (seconds)"+timeInSeconds/60.0+"\t minutes");
			System.out.println("Printing the array...");
			for(int i=0; i<5;i++){
				System.out.println();			
				for(int j=0; j<testSuite.length; j++){
					System.out.print("res["+i+","+j+"]="+resultsTable[i][j]+"\t");				
				}			
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultsTable;
	}	
	
	private static synchronized String[] executeTestSuite(String swsId, TestCase[] testSuite){
		String[] outputs=null;
		outputs=new String[testSuite.length];
		for(int i=0;i<testSuite.length;i++){
			String testInput=testSuite[i].getTestInput();
			TestCaseExecuter tExec= new TestCaseExecuter();
			outputs[i]= tExec.executeTestCase(swsId, testInput);			
		}		
		return outputs;		
	}	
	//Makes the mutant Web service, uploads that and returns the name of the mutant SWS Id from that.
	private static synchronized String makeSWSFromMutant(Mutant m) throws Exception {		
		
		//System.out.println("\nMaking Mutant SWS from mutant"+ m.getMutantId()+"This includes following tasks:" );		
		String mutantSWSId=null;
		try{			
			//FIXME later one, automatically determine the list of the directories. Make it hard coded for the time being.
			File mutationDirectory= new File("C:\\muJavaTool\\result\\com.discount.DiscountWebServiceSkeleton\\traditional_mutants\\com.discount.PurchaseResponseDocument_purchase(com.discount.PurchaseRequestDocument)");
			
			File mutantDir= new File (mutationDirectory, m.getMutantName());
			//Replace the web service implementation file (XXXSkeleton.java) in the AXIS WORK directory, with the mutated file from Mutants Directory:									
			File deployedFile= DeployWSDLWebService.getRequiredFile(".aar", new File(DeployWSDLWebService.AXIS2_location_Str)).get(0);			
			System.out.println("Replacing the original file with the mutant file"+m.getMutantName());
			DeployWSDLWebService.replaceFileWith(DeployWSDLWebService.getRequiredFile("Skeleton.java", DeployWSDLWebService.WSWorkDIR_file).get(0), DeployWSDLWebService.getRequiredFile("Skeleton.java", mutantDir).get(0));
			System.out.println("Replaced..");			
			System.out.println("Generating Deployable Jar!");
			DeployWSDLWebService.generateDeployableWebService();
			System.out.println("Jar Generated!");			
			System.out.println("Uploading the jar to Web server Temp directory");			
			DeployWSDLWebService.uploadWebService(DeployWSDLWebService.getRequiredFile("DiscountWebService.aar", DeployWSDLWebService.WSWorkDIR_file).get(0).getName());
			//sleep the thread then it will be uploaded!
			System.out.println("Main thread sleeping");
			Thread.sleep(5000);
			System.out.println("Main thread woke up");
			if(deployedFile.exists()){ 
				File mutantSWSFile=MutantsGenerator.makeMutantSWSFile(m.getMutantName(),  originalSWS);				
				if(mutantSWSFile.exists())			
					mutantSWSId = MutantsGenerator.getSWSIdentifier(mutantSWSFile);
			}					
			else 
				System.out.println("Error occurred..");
		}catch(FileNotFoundException fne){
			fne.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return mutantSWSId;		
	}

	//Tested: OK!
	//Made public to be used outside the class..
	public static Mutant[] generateMutantsInfo(){				
		String[] mutantNames= MutantsGenerator.getMutantNames();
		Mutant[] mutants= new Mutant[mutantNames.length];
		for(int i=0;i<mutantNames.length;i++){
			Mutant m= new Mutant("m"+(i),mutantNames[i]);
			mutants[i]=m;			
		}		
		return mutants;	
	}
	private static void startWSMXServer(){
		File buildFile=new File("build.xml");		   
		Project p = new Project();		   
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());		   
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);		   
		helper.parse(p, buildFile);
		p.executeTarget("start");		
	}

	public static void main(String[] args) throws Exception{
		String orginalSWSId="http://www.wsmo.org/sws-challenge/discountWS#DiscountWS";
		//performMutationEvaluation2(orginalSWSId, testSuite);	
		
	    /* int[][] results={
				{1,0,0,1,1},
				{0,1,0,0,0},
				{0,0,1,1,0},
				{0,1,0,1,0},
				{0,0,0,0,1}		
		};
		
		System.out.println("Printing the results array...!");
		
		for (int i=0;i<5; i++){
			for(int j=0; j<5; j++)
				System.out.print("\t"+results[i][j]);
			System.out.println();
		}
		
		
		
		Hashtable<Mutant, Set<TestCase>> ht= getMutantsTestCasesHT(results);	
		
		double muScore= calculateMutationScore(ht);
		System.out.println("The mutation score is\t"+muScore);		
				
		Set<Mutant> mutantss=ht.keySet();
		for(Mutant m: mutantss){
			Set<TestCase> tests=null;						
			System.out.println("Test Cases killing the mutant\t"+m.getMutantId());
			
			tests= ht.get(m);
			for(TestCase t: tests){				
				System.out.print("t"+t.getTestId()+"\t");
			}
			System.out.println();	
		}*/				
	}
}
