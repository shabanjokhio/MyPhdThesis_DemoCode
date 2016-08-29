package com.muSWS.core;

import java.io.File;
import java.util.*;


import edu.emory.mathcs.backport.java.util.Arrays;
/*
 * This class performs the Results and Analysis. Mainly calculate the mutation score based on the number of the faults detected. Also, can perform the side analysis:
 */

public class ResultsAndAnalysis {
	
	public static TestCase[] testSuite= new TestCase[5];
	public static Hashtable <File, String[]> filesAndLinesRemoved= new Hashtable<File,String[]>();
	public static Hashtable <Mutant, Set<TestCase>> mutantsTestCasesHT = new Hashtable<Mutant, Set<TestCase>>();
	public static Set<TestCase> universalRedundantTestCases=new TreeSet<TestCase>();
	public static Mutant[] mutants = MuSWSEvaluation.generateMutantsInfo();	
	

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
		
	public static double calculateMutationScore(Hashtable <Mutant, Set<TestCase>> mutantsTestsHT){
		double muScore=0.00;
		Set <Mutant> mutantsKeysSet= mutantsTestsHT.keySet();
		int NoOfFaultsInjected= mutantsKeysSet.size();
		int NoOfFaultsDetect=0;
		for(Mutant m: mutantsKeysSet){
			Set<TestCase> testsDetectingThisFault= mutantsTestsHT.get(m);
			if (testsDetectingThisFault.size()>=1) // if there is at least one test case killing this mutant, then the fault is detected. increment fd counter:
				NoOfFaultsDetect++;
		}
		muScore=NoOfFaultsDetect/NoOfFaultsInjected;
		return muScore;
	}
	
	public static Set<Mutant> getMutantsKilledByAllTestCases(Set<TestCase> tests){
		Set<Mutant> maxMutantsKilled=null;
		maxMutantsKilled=new TreeSet<Mutant>();
		
		for (TestCase t: tests){
			Set<Mutant> mutantsKilledByCurrentTest= getMutantsKilledByATestCase(t);
			maxMutantsKilled.addAll(mutantsKilledByCurrentTest);	
		}		
		return maxMutantsKilled;		
	}
	public static Set<TestCase> _determineReduceTestSuite(int[][] results){
		Set<TestCase> reducedTestSet=null;		
		reducedTestSet= new TreeSet<TestCase>();
		System.out.println("Algorithm started...");	
		
		Set<TestCase> testSetOriginal = getTestSuiteAsASet(MuSWSEvaluation.testSuite); // combine two methods: 
		Set<TestCase> testSet= filterRedundantTestCases(testSetOriginal, results);
		
		System.out.println("Step 1. Getting test suite (T) and mutants killed by all test cases M(K), from results[][]");
		Set<Mutant> mutantsKilled =_getMutantsKilledByAllTestCases(getTestSuiteAsASet(MuSWSEvaluation.testSuite), results);
			
		System.out.println("\nStep A: Entering into the while loop...");
		
		int i=1;
		while (true) {			
			Set<TestCase> testCasesSubsumed=null;
			System.out.println("ITERATION No.\t"+(i++) +"\n");			
			printInfo(results);				
			TestCase theMostEffectiveTestCase=null;			
			System.out.println("\nStep 2: Getting the most effective test case from T. Also remove it from T\n");
			theMostEffectiveTestCase = getTestCaseKillingMostMutants(testSet, results);
			System.out.println("The most effective test case is\t t"+theMostEffectiveTestCase.getTestId());
						
			System.out.println("Step 3: Adding the most effective test case into the Reduced Set Tr");		
			reducedTestSet.add(theMostEffectiveTestCase);
			System.out.println("Added!\n");
						
			System.out.println("Step 4: Getting test cases subsumed by most effective test Tsub(ti) case t"+theMostEffectiveTestCase.getTestId());			
			testCasesSubsumed = getTestCasesSubsumedBySpecifiedTestCase(theMostEffectiveTestCase, testSet, results);
			System.out.println("No. of test cases subsumed by\t t"+theMostEffectiveTestCase.getTestId()+"\tis\t"+testCasesSubsumed.size());			
			
			for(TestCase t: testCasesSubsumed)
				System.out.println("t"+t.getTestId());			
			
			System.out.println("Step 5: Removing test cases subsumed from T");
			testSet.removeAll(testCasesSubsumed);
			System.out.println("Removed!\n");
			
			System.out.println("Printing the test suite after updating");			
			for(TestCase t: testSet)
				System.out.println("t"+t.getTestId());
				
			System.out.println("Step 6: Getting the No. of mutants killed by\t t"+theMostEffectiveTestCase.getTestId()+"\tare\t"+_getMutantsKilledByATestCase(theMostEffectiveTestCase, results).size());
			Set<Mutant> mutantsKilledByMostEffectiveTestCase = _getMutantsKilledByATestCase(theMostEffectiveTestCase, results);				
			System.out.println("\nMutants killed by \t t"+theMostEffectiveTestCase.getTestId());
			for (Mutant m: mutantsKilledByMostEffectiveTestCase)
				System.out.println("Mutant\t"+m.getMutantId());
			
											
			System.out.println("Step 7: Removing the all mutants killed by\t t"+theMostEffectiveTestCase.getTestId()+"\tfrom the mutant set");			
			mutantsKilled.removeAll(mutantsKilledByMostEffectiveTestCase); //All the mutants killed by the most effective mutant are removed...
			System.out.println("Removed!");
			
			System.out.println("Print remaining mutants");
			for (Mutant m:mutantsKilled)
				System.out.println("Mutant\t"+m.getMutantId());
						
			System.out.println("Updating the original results array. Insert -1 in a row, if that mutant is already killed");
			updateResultsArray(mutantsKilledByMostEffectiveTestCase, results);
			System.out.println("Updated");
						
			System.out.println("Step 8. Check if all mutants are covered...");
			if(mutantsKilled.isEmpty()){
				System.out.println(".....Step 8.1: All mutants are covered. Break out of loop!\n");
				break;
			}
			else{
				System.out.println("..... Step 8.2: All mutants are not covered. Continue loop from step A: )\n");
				continue;				
			}
		}
		System.out.println("..... Step 9: Returning the reduced Test Set)\n");		
		return reducedTestSet;	
	}	
	
	private static void updateResultsArray(Set<Mutant> mutantsAlreadyKilled, int[][] results){
		int muIndex=0;
		for(Mutant m: mutantsAlreadyKilled){
			muIndex=getIndexForAMutant(m);
			for(int j=0;j<MuSWSEvaluation.testSuite.length;j++)
				results[muIndex][j]=-1; // -1 in a row, indicates that this mutant is already killed			
		}	
	}
	private static int getIndexForAMutant(Mutant m){
		int muIndex=0;
		for(int i=0;i<5;i++)
			if(mutants[i].getMutantId().equals(m.getMutantId()))				
				muIndex=i;
		return muIndex;
	}
	private static int getIndexForTest(TestCase t){
		int testIndex=0;
		for(int i=0; i<testSuite.length; i++)
			if(testSuite[i].equals(t))
				testIndex=i;		
		return testIndex;
	}
	//From the given results, results the Hashtable of the Mutants and the Set of Test cases killing that mutant.
	public static Hashtable <Mutant, Set<TestCase>> getMutantsTestCasesHT(int[][] results){				
		for(int i=0;i<5;i++){
			Set<TestCase> testCasesKillingThisMutant= new TreeSet<TestCase>();
			for(int j=0;j<testSuite.length;j++){
				if(results[i][j]==1){
					testCasesKillingThisMutant.add(testSuite[j]);
				}		
			}
			mutantsTestCasesHT.put(mutants[i], testCasesKillingThisMutant);
		}		
		return mutantsTestCasesHT;
	}	
	
	
	private static Set<TestCase> getTestCasesKilingAMutantUniversal(Mutant m){		
		return mutantsTestCasesHT.get(m);		
	}
	
	//Tested: Correct
	private static Set<TestCase> _getTestCasesKillingAMutant(Mutant m, int[][] results){
		TestCase[] testsKillingAMutant=null;
		Set<TestCase> testSetKillingAMutant= null;
		
		testSetKillingAMutant=new TreeSet<TestCase>();		
		int muIndex=0;
		for(int i=0;i<mutants.length;i++)
			if(mutants[i].getMutantId().equals(m.getMutantId()))				
				muIndex=i;
		
		for(int j=0; j<MuSWSEvaluation.testSuite.length; j++){
			if (results[muIndex][j]==1)
				testSetKillingAMutant.add(MuSWSEvaluation.testSuite[j]);
		}
		/*Object[] testsObjs= new Object[testSetKillingAMutant.size()];
		testsObjs=testSetKillingAMutant.toArray();
		testsKillingAMutant= new TestCase[testsObjs.length];
		for(int i=0;i<testsKillingAMutant.length;i++)
			testsKillingAMutant[i]=(TestCase)testsObjs[i];*/
		
		//return testsKillingAMutant;
		return testSetKillingAMutant;
	}
	
	
	
	
	
	private static Set<TestCase> getTestCasesKillingAMutant(Mutant m, Set<TestCase> testSuiteSpecific){
		Set <TestCase> testCasesKillingMutant = null;
		Set <TestCase> completeTestSuite=getTestSuiteAsASet(testSuite);
		if (completeTestSuite.containsAll(testSuiteSpecific))
			completeTestSuite.retainAll(testSuiteSpecific); //T=T-Ts		
		
		testCasesKillingMutant = mutantsTestCasesHT.get(m);
		
		return	testCasesKillingMutant;
	}
	//Tested: Correct
	private static Set<Mutant> _getMutantsKilledByAllTestCases( Set<TestCase> testCases, int[][] results){
		//Mutant[] maxMutantsKilled=null;
		Set<Mutant> maxMutantsKilledSet=null;
		maxMutantsKilledSet=new TreeSet<Mutant>();
		
		for (TestCase t: testCases){
			Set<Mutant> mutantsKilledByCurrentTest= _getMutantsKilledByATestCase(t, results);			
			for(Mutant m: mutantsKilledByCurrentTest)
				maxMutantsKilledSet.add(m);
		}
		/*Object[] maxMutantsKilledArrayObjs= maxMutantsKilledSet.toArray();
		maxMutantsKilled= new Mutant[maxMutantsKilledArrayObjs.length];
		for(int i=0;i<maxMutantsKilled.length;i++)
			maxMutantsKilled[i]=(Mutant)maxMutantsKilledArrayObjs[i];*/
				
		return maxMutantsKilledSet;		
	}
	
	//Tested: Correct
	private static Set<Mutant> _getMutantsKilledByATestCase(TestCase t, int[][] results){		
		Set<Mutant> mutantsSetKilledByT=null;		
		mutantsSetKilledByT= new TreeSet<Mutant>();		
		int testIndex=0;
		for(int i=0; i<testSuite.length; i++)
			if(testSuite[i].equals(t))
				testIndex=i;		
		
		for(int i=0;i< 5;i++)
			if(results[i][testIndex]==1)
				mutantsSetKilledByT.add(mutants[i]);
		
		/*Object[] muObjs= new Object[mutantsSetKilledByT.size()];
		muObjs=mutantsSetKilledByT.toArray();
		mutantsKilledByT= new Mutant[muObjs.length];
		for(int i=0;i<mutantsKilledByT.length;i++)
			mutantsKilledByT[i]=(Mutant)muObjs[i];*/
		
		return mutantsSetKilledByT;		
	}
	
	
	private static Set<Mutant> getMutantsKilledByATestCase(TestCase t){ //determining tests for the mutants is easier. However, it is also desirable that which mutants are killed by one test case:
		Set<Mutant> mutantsKilledByT=null; // reset the mutants killed...
		mutantsKilledByT= new TreeSet<Mutant>();
		Set<Mutant> allMutants= mutantsTestCasesHT.keySet();		
		for(Mutant m: allMutants){
			Set<TestCase> testsKillingThisMutant=mutantsTestCasesHT.get(m);			
				for (TestCase tt : testsKillingThisMutant) {
					if (tt.equals(t))
						mutantsKilledByT.add(m);
				}			
				//System.out.println("No mutant is killed by this test case\t"+mutantsKilledByT.size());
				testsKillingThisMutant= new TreeSet<TestCase>();			
		}		
		return mutantsKilledByT;
	}
	
	private static int getNoOfMutantsKilledByATestCase(TestCase t){
		int numberOfMutantsKilled=0;		
		numberOfMutantsKilled=getMutantsKilledByATestCase(t).size();		
		return numberOfMutantsKilled;		
	}
	
	private static Set<TestCase> filterRedundantTestCases(Set<TestCase> testSuiteInitial, int[][] results){		
		for (TestCase t: testSuiteInitial){
			int mutantsKilled=_getMutantsKilledByATestCase(t, results).size();
			if(mutantsKilled==0){
				universalRedundantTestCases.add(t);					
			}
		}
		testSuiteInitial.removeAll(universalRedundantTestCases);
		return testSuiteInitial;
	}
	
	private static TestCase getTestCaseKillingMostMutants(Set<TestCase> testSuite, int[][] results){	
		
		TestCase testKillingMostMutants=null;		
		int maximumMutants=0;
		
			for (TestCase t : testSuite) {
				Set<Mutant> mutantsKilledByCurrentTestCase = _getMutantsKilledByATestCase(t, results);
				int numberOfMutantsKilledByCurrentTestCase = mutantsKilledByCurrentTestCase.size();
				if (numberOfMutantsKilledByCurrentTestCase > maximumMutants) {
					maximumMutants = numberOfMutantsKilledByCurrentTestCase;
					testKillingMostMutants = t;
				}
			}		
		return testKillingMostMutants;		
	}
	
	private static TestCase getTestCaseKillingLeastMutants(Set<TestCase> testSuite){
		TestCase testKillingLeastMutants=null;
		
		int minimumMutants=1;
		for(TestCase t: testSuite){
			Set<Mutant> mutantsKilledByCurrentTestCase= getMutantsKilledByATestCase(t);
			int numberOfMutantsKilledByCurrentTestCase=mutantsKilledByCurrentTestCase.size();
			if(numberOfMutantsKilledByCurrentTestCase <= minimumMutants){
				minimumMutants=numberOfMutantsKilledByCurrentTestCase;
				testKillingLeastMutants=t;				
			}		
		}		
		return testKillingLeastMutants;
	}
	
	public static Set<TestCase> getTestSuiteAsASet(TestCase[] testSuite){
		return new TreeSet<TestCase>(Arrays.asList(testSuite));		
	}
	
	/*public static SortedSet<TestCase> sortTestSuite (Set<TestCase> unsortedTestSuite){
		
		SortedSet<TestCase> sortedTestSuite= new TreeSet<TestCase>((SortedSet)unsortedTestSuite);//create set from the sorted List
		return sortedTestSuite; 
	}
	
	private static SortedSet<Mutant> sortMutantSet(Set<Mutant> unsortedMutantSet){
		SortedSet<Mutant> sortedMutantSet= new TreeSet<Mutant>((SortedSet)unsortedMutantSet);
		return sortedMutantSet;
		
	}*/
	
	public static TestCase[] getTestSuiteAsAnArray(Set<TestCase> testSuiteSet){
		TestCase[] testSuiteArray=null;
		testSuiteArray= new TestCase[testSuiteSet.size()];		
		int i=0;
		for(TestCase t: testSuiteSet){			
			testSuiteArray[i]=t;
			i++;			
		}		
		return testSuiteArray;		
	}
	
	private static boolean isRedundantTestCase(TestCase testCase){
		boolean isRedundant=false;	
		Set <Mutant> mutantsKilledByThisTestCase = getMutantsKilledByATestCase(testCase);		
		for(int i=0; i<testSuite.length; i++){
			Set<Mutant> mutantsKilledByCurrentTestCase =getMutantsKilledByATestCase(testSuite[i]);			
			if (testSuite[i].getTestId()!=testCase.getTestId()){
				if(mutantsKilledByCurrentTestCase.containsAll(mutantsKilledByThisTestCase)){
					isRedundant=true;
				}
			}
			else continue;
		}	
		return isRedundant;		
	}
	
	//returns the test case that is the proper superset of the test case specified in the argument.
	private static TestCase getTestCaseSubsumingRedundantTestCase(TestCase testCase){		
		TestCase subsumedBy=null;
		Set <Mutant> mutantsKilledByThisTestCase = getMutantsKilledByATestCase(testCase);		
		for(int i=0; i<testSuite.length; i++){
			Set<Mutant> mutantsKilledByCurrentTestCase =getMutantsKilledByATestCase(testSuite[i]);			
			//if (testSuite[i].getTestId()!=testCase.getTestId()){
			if (!testSuite[i].equals(testCase)){//compares the test Ids only
				if(mutantsKilledByCurrentTestCase.containsAll(mutantsKilledByThisTestCase)){
					subsumedBy = testSuite[i];
				}
			}
			else continue;
		}			
		return subsumedBy;	
	}
	
	/*private static Set<TestCase> _getTestCasesSubsumedBySpecifiedTestCase(TestCase testCase, TestCase[] testSuiteArr){
		
		Set<TestCase> testSuite= new TreeSet(Arrays.asList(testSuiteArr));
			
		TestCase[] testsSubsumed=null;
		Set<TestCase> subsumedTestCases=null;
		
		subsumedTestCases= new TreeSet<TestCase>();
		Set<Mutant> testCasesKilledByMostEffectiveTestCase= getMutantsKilledByATestCase(testCase);		
		testSuite.remove(testCase); //Remove the same test case
		for(TestCase t: testSuite){
			Set<Mutant> mutantsKilledByCurrentTestCase = getMutantsKilledByATestCase(t);
			if (mutantsKilledByCurrentTestCase==null){
				subsumedTestCases.add(t);
			} else if(testCasesKilledByMostEffectiveTestCase.containsAll(mutantsKilledByCurrentTestCase)){				
				subsumedTestCases.add(t);				
			}
		}
		filterRedundantTestCases(testSuite);
		subsumedTestCases.addAll(universalRedundantTestCases);
		Object[] testsSubsumedArrayObjs=subsumedTestCases.toArray();
		testsSubsumed= new TestCase[testsSubsumedArrayObjs.length];
		for(int i=0;i<testsSubsumed.length;i++)
			testsSubsumed[i]=(TestCase)testsSubsumedArrayObjs[i];
		return testsSubsumed;		
	}*/
	
	private static Set<TestCase> getTestCasesSubsumedBySpecifiedTestCase(TestCase testCase, Set<TestCase> testSuite, int[][] results){
		Set<TestCase> subsumedTestCases=null;
		subsumedTestCases= new TreeSet<TestCase>();
		Set<Mutant> mutantsKilledByMostEffectiveTestCase= _getMutantsKilledByATestCase(testCase, results);
		//System.out.println("\n Inside Method: Printing the mutants killed by most effective test case\t t"+testCase.getTestId());
		//for(Mutant m: mutantsKilledByMostEffectiveTestCase)
			//System.out.println(m.getMutantId());
		
		System.out.println();
		
		testSuite.remove(testCase); //Remove the most effective test case itself:
		for(TestCase t: testSuite){
			Set<Mutant> mutantsKilledByCurrentTestCase = _getMutantsKilledByATestCase(t, results);
			if (mutantsKilledByCurrentTestCase.size()==0){
				subsumedTestCases.add(t);
			} else if(mutantsKilledByMostEffectiveTestCase.containsAll(mutantsKilledByCurrentTestCase)){				
				subsumedTestCases.add(t);				
			}
		}
		
		filterRedundantTestCases(testSuite, results); // filter those test cases that are already redundant, i.e., does not kill any mutant.
		//System.out.println("universal redundant test cases\t"+universalRedundantTestCases.size());
		subsumedTestCases.addAll(universalRedundantTestCases);
		return subsumedTestCases;		
	}			
	//get the Hashtable of the Test cases and the set of the mutants killed by that test case.
	public static Hashtable<TestCase, Set<Mutant>> getTestCasesMutantsHT(Set<TestCase> testCases){
		Hashtable<TestCase, Set<Mutant>> testCasesMutantsHT=null; 
		testCasesMutantsHT = new Hashtable<TestCase, Set<Mutant>>();
		
		for(TestCase t: testCases){
			Set<Mutant> mutantsKilled=getMutantsKilledByATestCase(t);			
			testCasesMutantsHT.put(t, mutantsKilled);
		}	
		return testCasesMutantsHT;
	}
	
	private static void printInfo(int[][] results){
		
		System.out.println("\nPrinting the results array:");
		for(int i=0;i<5; i++){
			for(int j=0; j<MuSWSEvaluation.testSuite.length; j++)
				System.out.print(results[i][j]+"\t");
			System.out.println();
		}
			
		System.out.println("\nPrinting the mutants and test cases killing each mutant\n");
		System.out.println("Mutants|\t\t TestCases");
		System.out.println("------------------------------");
		for(int i=0; i<5; i++){
			System.out.print("Mutant\t"+mutants[i].getMutantId()+":\t");
			for (int j=0; j<testSuite.length;j++){
				if (results[i][j]==1)
					System.out.print("t"+testSuite[j].getTestId()+"\t");				
			}
			System.out.println("");
		}
	}
		
	public static void main (String[] args){
		int[][] results = {
				{0,0,0,1,0},
				{1,0,0,0,1},
				{0,1,1,1,0},
				{0,0,0,1,0},
				{0,1,0,0,1}		
		};
		
	/*	printInfo(results);
		System.out.println("---------------------------------");
		Set<Mutant> mutantsKilled=null;
		for(int i=0;i<MuSWSEvaluation.testSuite.length;i++){
			mutantsKilled= _getMutantsKilledByATestCase(MuSWSEvaluation.testSuite[i], results);
			System.out.println("Mutants killed by\t t"+MuSWSEvaluation.testSuite[i].getTestId());
			for(Mutant m: mutantsKilled)
				System.out.print(m.getMutantId()+"\t");
			System.out.println();			
		}
		
		System.out.println("-----------------------------------");
		System.out.println("Mutants Killed by All Test cases...");
		
		Set<Mutant> mutantsKilledTotal= _getMutantsKilledByAllTestCases(getTestSuiteAsASet(MuSWSEvaluation.testSuite), results);		
		for(Mutant m: mutantsKilledTotal)
			System.out.print(m.getMutantId()+"\t");
		
	
		Set<TestCase> testsSubsumedByT= getTestCasesSubsumedBySpecifiedTestCase(MuSWSEvaluation.testSuite[3], getTestSuiteAsASet(MuSWSEvaluation.testSuite), results);
		System.out.println("Printing the Subsumed test suite...by \t t"+MuSWSEvaluation.testSuite[3].getTestId());
		for(TestCase t: testsSubsumedByT)
			System.out.print("t"+t.getTestId()+"\t");			
		 		
		Set<TestCase> testSuite=getTestSuiteAsASet(MuSWSEvaluation.testSuite);
		
		TestCase mostEffective1= getTestCaseKillingMostMutants(testSuite, results);		
		System.out.println("\nMost effective test Case is\t t"+mostEffective1.getTestId());
			*/

		Set<TestCase> redTestSet= _determineReduceTestSuite(results) ;
		System.out.println("Printing the reduced test suite, Tr:");
		for(TestCase t: redTestSet)
			System.out.println("t"+t.getTestId()+"\t"); 
		
		
		
		
	} //Eof main:
}
