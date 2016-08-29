package com.muSWS.core;

import java.io.IOException;
import java.util.Set;

import org.wsmo.common.exception.InvalidModelException;
import org.wsmo.wsml.ParserException;

/*
 * This class is responsible to Compare output of two values...
 */
public class TestOracle {

	public boolean stringComparison(String originalOutput, String mutantWSOutput){		
		if (originalOutput.equalsIgnoreCase(mutantWSOutput))
			return true;
		else
			return false;	
	}

	public static synchronized boolean valueComparison(String originalOutput, String mutantOutput) throws IOException, ParserException, InvalidModelException{
		boolean valueEquals=false;
		org.omwg.ontology.Value origValue=null;
		org.omwg.ontology.Value muValue=null;

		if (originalOutput== null){
			System.out.println("Original Output is NULL");
			if (mutantOutput==null){
				System.out.println("Both Original and Mutant Output are NULL");
				return true; //both are equal, both are null
			}
			else if (mutantOutput!=null){
				System.out.println("Original output is Null....Mutant Output is NOT NULL");
				return false;	
			}
		}
		else if (mutantOutput==null){ //Original output is NOT Null: usual case:
			System.out.println("Original output is NOT Null, Mutant is Null");
			return false;
		}else if (originalOutput!= null && mutantOutput!=null ){  //Both mutant and original outputs are NOT NULL...
			//System.out.println("Both mutant and original outputs are NOT NULL...");
			if (originalOutput.contains("Failure:") || originalOutput.contains("failure") || originalOutput.contains("terminated") || originalOutput.contains("rollback") || mutantOutput.contains("Failure:") || mutantOutput.contains("failure") || mutantOutput.contains("terminated") || mutantOutput.contains("rollback")){
				System.out.println("Fault Occured in original or mutant SWS...");
				return false;
			}
			else {				
								
				org.omwg.ontology.Ontology originalOutputOntology=  (org.omwg.ontology.Ontology)at.sti.wsmx.client.util.ParserAndSerializer.parseTopEntity(originalOutput);				
				org.wsmo.factory.WsmoFactory wsmoFact = org.wsmo.factory.Factory.createWsmoFactory(null);
				org.wsmo.common.Namespace ns= wsmoFact.createNamespace("disc", wsmoFact.createIRI("http://www.wsmo.org/sws-challenge/discountOnto#"));					

				Set <org.omwg.ontology.Instance> instances= originalOutputOntology.listInstances();				
				for(org.omwg.ontology.Instance i: instances){							
					Set<org.omwg.ontology.Value> originalValues= i.listAttributeValues(wsmoFact.createIRI(ns,"respMsg"));				
					for(org.omwg.ontology.Value value:originalValues){	
						origValue=value;						
					}
				}			
						
				org.omwg.ontology.Ontology mutantOutputOntology= (org.omwg.ontology.Ontology)at.sti.wsmx.client.util.ParserAndSerializer.parseTopEntity(mutantOutput);						 
				Set <org.omwg.ontology.Instance> instancesM= mutantOutputOntology.listInstances();				
				for(org.omwg.ontology.Instance i: instancesM){							
					Set<org.omwg.ontology.Value> mutantValues= i.listAttributeValues(wsmoFact.createIRI(ns,"respMsg"));					
					for(org.omwg.ontology.Value value:mutantValues){
						muValue=value;										
					}
				}					 
			}	
		}
		if (muValue.equals(origValue))	
			valueEquals=true; 
		else 
			valueEquals = false;
		
		return valueEquals;
	}
	
	public static synchronized String getValueFromOutput(String output){
		String outputValue=null;
		
		org.omwg.ontology.Value origValue=null;				
		org.omwg.ontology.Ontology originalOutputOntology=null;
		try {			
			if (output==null){
				outputValue="NULL: Not initialized..!";
				return outputValue;								
			}else{
				if (output.contains("Failure:") || output.contains("failure") || output.contains("terminated") || output.contains("rollback")){
					System.out.println("Fault occured");
					outputValue="NULL: Not initialized..!";
					return outputValue;
				}
				else
				originalOutputOntology = (org.omwg.ontology.Ontology)at.sti.wsmx.client.util.ParserAndSerializer.parseTopEntity(output);
			}		
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (ParserException e) {			
			e.printStackTrace();
		} catch (InvalidModelException e) {		
			e.printStackTrace();
		}
		org.wsmo.factory.WsmoFactory wsmoFact = org.wsmo.factory.Factory.createWsmoFactory(null);
		org.wsmo.common.Namespace ns= wsmoFact.createNamespace("disc", wsmoFact.createIRI("http://www.wsmo.org/sws-challenge/discountOnto#"));					
		
		Set <org.omwg.ontology.Instance> instances= originalOutputOntology.listInstances();	
		
		for(org.omwg.ontology.Instance i: instances){							
			Set<org.omwg.ontology.Value> originalValues= i.listAttributeValues(wsmoFact.createIRI(ns,"respMsg"));				
			for(org.omwg.ontology.Value value:originalValues){	
				outputValue=value.toString();						
			}
		}		
		return outputValue;		
	}

	public static void main (String args[]) throws IOException, ParserException, InvalidModelException {		
		String originalOutput =	"namespace \t {\n _\"http://www.wsmo.org/sws-challenge/discountOnto#\",\n " +
				"dc \t _\"http://purl.org/dc/elements/1.1#\" \t}\n" +
				"ontology \t GENID1323220920488\n" +
				"concept purchaseRes\n" +
				"respMsg \t ofType \t _string\n" +
				"instance \t _\"http://example.com/resp\" memberOf purchaseRes\n" +
				"respMsg hasValue \"Senior Special Discount\"\n"+ "";

		String mutantOutput =	"namespace \t {\n _\"http://www.wsmo.org/sws-challenge/discountOnto#\",\n " +
			"dc \t _\"http://purl.org/dc/elements/1.1#\" \t}\n" +
			"ontology \t GENID1323220920488\n" +
			"concept purchaseRes\n" +
			"respMsg \t ofType \t _string\n" +
			"instance \t _\"http://example.com/resp\" memberOf purchaseRes\n" +
			"respMsg hasValue \"Senior Special Discount\"\n"+ "";
		
		System.out.println("\n\nValues Equal\t"+valueComparison(originalOutput, mutantOutput ));	
			
	}
}
