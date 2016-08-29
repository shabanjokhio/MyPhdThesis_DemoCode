package com.muSWS.test;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axiom.soap.impl.llom.soap12.SOAP12Factory;
import org.apache.axis2.client.ServiceClient;

import at.sti.wsmx.client.entrypoint.WSMXEntryPointsStub;
import at.sti.wsmx.client.entrypoint.WSMXEntryPointsStub.InvokeWebService;
//import at.sti.wsmx.client.util.ParserAndSerializer;

/*
 * This class will test whole project: or individual modules with or without GUI
 */

public class TestClass {
	
private static WSMXEntryPointsStub stub=null;
	
	public static void main(String[] args) throws XMLStreamException, FactoryConfigurationError, Exception{
		
		String wsmlWebService1="http://www.wsmo.org/sws-challenge/WSHello#WSHello2";		
		String wsmlWebService2="http://www.wsmo.org/sws-challenge/WSAmazon#AmazonWS2";
		
		String wsmlOnto1="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
				"namespace {_\"http://www.wsmo.org/sws-challenge/HelloGoal#\", ho _\"http://www.wsmo.org/sws-challenge/hello#\"}\n" +
				"ontology HelloGoalRequest\n" +
				"importsOntology{\n" +
				"ho#HelloOnto1}\n" +
				"instance helloRequest1 memberOf ho#HelloRequest\n" +
				"ho#personName hasValue \"Muhammad\"" +
				"ho#gender hasValue \"Male\"" +
				"ho#cAge hasValue 30";
		
		String wsmlOnto2="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://www.wsmo.org/sws-challenge/AmazonGoal#\", am _\"http://www.wsmo.org/sws-challenge/amazonOnto#\"}\n" +
		"ontology AmazonGoalRequest\n" +
		"importsOntology{\n" +
		"am#amazonOntology2}\n" +
		"instance myitemSearch memberOf am#itemSearch\n" +
		"am#awsAccessKeyId hasValue \"Shaban1234\"" +
		"am#request hasValue am#itemSearchRequest1";
		
		//stub= new WSMXEntryPointsStub("http://localhost:8050/axis/services/WSMXEntryPoints", "Admin", "Admin");
		stub= new WSMXEntryPointsStub("http://localhost:8050/axis/services/WSMXEntryPoints");
		ServiceClient sc= stub._getServiceClient(); // got service client, the easiest way to call service.
		
		// Create the authentication header block:
		SOAP12Factory factory = (SOAP12Factory) OMAbstractFactory.getSOAP12Factory();

		OMNamespace omNs = factory.createOMNamespace("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse");

		SOAPHeaderBlock headerBlock = factory.createSOAPHeaderBlock("Security", omNs);
		headerBlock.setMustUnderstand(true);
		OMElement userNameTokenElement = factory.createOMElement("UsernameToken", omNs);
		OMElement userNameElement = factory.createOMElement("Username", omNs);
		userNameElement.setText("Admin");
		OMElement passwordElement = factory.createOMElement("Password", omNs);
		passwordElement.setText("Admin");
		userNameTokenElement.addChild(userNameElement);
		userNameTokenElement.addChild(passwordElement);
		headerBlock.addChild(userNameTokenElement);

		sc.addHeader(headerBlock);	
		
		WSMXEntryPointsStub.InvokeWebService invokeWebServiceRequest=null;
		invokeWebServiceRequest= new InvokeWebService();
		WSMXEntryPointsStub.InvokeWebServiceResponse invokeWSResponse=null;	
		
		invokeWebServiceRequest.setWsmlWebService(wsmlWebService1);
		invokeWebServiceRequest.setWsmlMessage(wsmlOnto1);		
		
		invokeWSResponse= stub.invokeWebService(invokeWebServiceRequest);				
		
		String resStr= invokeWSResponse.get_return();	
		
		System.out.println("The output resposne is\n\n");		
		System.out.println(resStr);
		
	}
	

}
