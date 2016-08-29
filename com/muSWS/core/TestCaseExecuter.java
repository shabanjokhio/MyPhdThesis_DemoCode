package com.muSWS.core;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axiom.soap.impl.llom.soap12.SOAP12Factory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.deployment.DeploymentEngine;
import org.apache.axis2.description.AxisServiceGroup;
import org.apache.axis2.engine.AxisConfiguration;
import org.wsmo.common.exception.InvalidModelException;
import org.wsmo.wsml.ParserException;

import com.muSWS.deployment.DeployWSDLWebService;

import at.sti.wsmx.client.entrypoint.ExceptionException0;
import at.sti.wsmx.client.entrypoint.WSMXEntryPointsStub;
import at.sti.wsmx.client.entrypoint.WSMXEntryPointsStub.InvokeWebService;

/*
 * This class is responsible to execute a single Test case by passing it to the WSMX. 
 */

public class TestCaseExecuter {	
	
	public static void main(String[] args) throws AxisFault{
		
		System.out.println("Deploying service HelloService2\t"+uploadWebService2("HelloService2.aar"));
		
		//System.out.println("Deployed Web service HelloService2\t"+undeployWebService("HelloService2"));
		
		String testInput1= "wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
		"namespace {_\"http://testcases.com/instances#\", disc _\"http://www.wsmo.org/sws-challenge/discountOnto#\"}\n" +
		"ontology testOntology\n" +
		"importsOntology{\n" +
		"disc#DiscountOntology }\n" +
		"instance testCase1 memberOf disc#purchaseReq\n" +
		"disc#cust2  hasValue disc#customer1\t " +
		"disc#itm2 hasValue disc#item1" +
		"disc#qty2 hasValue 1" +
		"disc#pAmount2 	hasValue 200" ;
		
		String orginalSWS="http://www.wsmo.org/sws-challenge/discountWS#DiscountWS";
		
		TestCaseExecuter t= new TestCaseExecuter();
		String output= t.executeTestCase(orginalSWS, testInput1);
		System.out.println(output);
		
		
		/*String testInput1="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
				"namespace {_\"http://www.wsmo.org/sws-challenge/HelloGoal#\", ho _\"http://www.wsmo.org/sws-challenge/hello#\"}\n" +
				"ontology HelloGoalRequest\n" +
				"importsOntology{\n" +
				"ho#HelloOnto1}\n" +
				"instance helloRequest1 memberOf ho#HelloRequest\n" +
				"ho#personName hasValue \"Muhammad\"\t " +
				"ho#gender hasValue \"Male\"\t " +
				"ho#cAge hasValue 18";
		
		String testInput2="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
				"namespace {_\"http://www.wsmo.org/sws-challenge/HelloGoal#\", ho _\"http://www.wsmo.org/sws-challenge/hello#\"}\n" +
				"ontology HelloGoalRequest\n" +
				"importsOntology{\n" +
				"ho#HelloOnto1}\n" +
				"instance helloRequest1 memberOf ho#HelloRequest\n" +
				"ho#personName hasValue \"Muhammad\"" +
				"ho#gender hasValue \"Male\"" +
				"ho#cAge hasValue 17";
		
		String testInput3="wsmlVariant _\"http://www.wsmo.org/wsml/wsml-syntax/wsml-rule\"\n"+
				"namespace {_\"http://www.wsmo.org/sws-challenge/HelloGoal#\", ho _\"http://www.wsmo.org/sws-challenge/hello#\"}\n" +
				"ontology HelloGoalRequest\n" +
				"importsOntology{\n" +
				"ho#HelloOnto1}\n" +
				"instance helloRequest1 memberOf ho#HelloRequest\n" +
				"ho#personName hasValue \"Muhammad\"" +
				"ho#gender hasValue \"Male\"" +
				"ho#cAge hasValue 19";
		
		
		Set<TestCase> testSuite= new HashSet<TestCase>();
		
		testSuite.add(new TestCase (1, testInput3, "OK"));
		
		testSuite.add(new TestCase (2, testInput1, "OK"));
		testSuite.add(new TestCase (3, testInput2, "OK"));
		
		String[] outputValues= new String[3];
		int i=0;
		for(TestCase ti: testSuite){
			String testInput=ti.getTestInput();
			outputValues[i]=new TestCaseExecuter().executeTestCase(orginalSWS, testInput);
			System.out.println(outputValues[i]);
		}
		*/
		
		//System.out.println("Done....");
		
	}
	
	public static synchronized boolean uploadWebService2(String wsFileName) throws AxisFault{
		boolean deployed=false;
		System.out.println("Deploying web Service"+wsFileName+"In the Tomcat");
		
		//ConfigurationContext cContext= ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF",
		//"C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");
		
		ConfigurationContext cContext= ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\axis2-1.5.5\\webapp\\WEB-INF","C:\\axis2-1.5.5\\conf\\axis2.xml");
		
		File libDir= new File(DeployWSDLWebService.WSWorkDIR_file, "build/lib");
		File aarFile= new File (libDir, wsFileName);
		AxisServiceGroup axisServiceGroup= DeploymentEngine.loadServiceGroup(aarFile, cContext);
		
		AxisConfiguration axisConfig= cContext.getAxisConfiguration();
		axisConfig.addServiceGroup(axisServiceGroup);
		
		String serviceGroupName = axisServiceGroup.getServiceGroupName();		
		System.out.println("The service group name is\t"+serviceGroupName);
		
		
		java.util.Iterator<AxisServiceGroup> iterator= axisConfig.getServiceGroups();
		System.out.println("Web services deployed in the Tomcat");
		while(iterator.hasNext()){
			AxisServiceGroup g=iterator.next();
			System.out.println(g.getServiceGroupName());
			if(g.equals(axisConfig.getServiceGroup(wsFileName))){
				deployed=true;
			}
		}
		return deployed;
	}
	
	public static synchronized boolean undeployWebService(String wsGroup) throws AxisFault{
		boolean undeployed=true;
		
		System.out.println("Ueploying web Service"+wsGroup+"From the Tomcat");
		//String wsGroup=wsFileName.substring(0, wsFileName.indexOf("."));
		
		ConfigurationContext cContext= ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF",
					"C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");		
		AxisConfiguration axisConfig= cContext.getAxisConfiguration();		
		axisConfig.removeServiceGroup(wsGroup);
		
		java.util.Iterator<AxisServiceGroup> iterator= axisConfig.getServiceGroups();
		System.out.println("Web services deployed in the Tomcat");
		int i=1;
		while(iterator.hasNext()){			
			AxisServiceGroup g=iterator.next();
			i++;
			System.out.println("Web Service No ."+ (++i)+"\t"+g.getServiceGroupName());
			if(g.equals(axisConfig.getServiceGroup(wsGroup))){
				System.out.println("Service group exists...");
				undeployed=false;
			}
		}		
		return undeployed;
	}
	
	private static WSMXEntryPointsStub stub=null;
	/*
	 * Executes a given test case on a given web service and returns the results in String:
	 */	
	
	public synchronized String executeTestCase(String SWSId, String testInput){		
		String output=null;
		try {			
			stub= new WSMXEntryPointsStub("http://localhost:8050/axis/services/WSMXEntryPoints");
			ServiceClient sc= stub._getServiceClient(); //Get service client, the easiest way to call service.				
			sc.addHeader(createAuthenticationHeaderBlock());
			
			WSMXEntryPointsStub.InvokeWebService invokeWebServiceRequest=null;
			invokeWebServiceRequest= new InvokeWebService();
			WSMXEntryPointsStub.InvokeWebServiceResponse invokeWSResponse=null;	
			
			invokeWebServiceRequest.setWsmlWebService(SWSId);
			invokeWebServiceRequest.setWsmlMessage(testInput);		
			
			invokeWSResponse= stub.invokeWebService(invokeWebServiceRequest);		
			output= invokeWSResponse.get_return();			
		} catch (RemoteException e) {			
			e.printStackTrace();
		}catch (ExceptionException0 ee){
			ee.printStackTrace();
		}		 
		return output;		
	}
	// Create the authentication header block:
	private SOAPHeaderBlock createAuthenticationHeaderBlock() {
		SOAP12Factory factory = (SOAP12Factory) OMAbstractFactory
				.getSOAP12Factory();

		OMNamespace omNs = factory
				.createOMNamespace(
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
						"wsse");

		SOAPHeaderBlock headerBlock = factory.createSOAPHeaderBlock("Security",
				omNs);
		headerBlock.setMustUnderstand(true);
		OMElement userNameTokenElement = factory.createOMElement(
				"UsernameToken", omNs);
		OMElement userNameElement = factory.createOMElement("Username", omNs);
		userNameElement.setText("Admin");
		OMElement passwordElement = factory.createOMElement("Password", omNs);
		passwordElement.setText("Admin");
		userNameTokenElement.addChild(userNameElement);
		userNameTokenElement.addChild(passwordElement);
		headerBlock.addChild(userNameTokenElement);

		return headerBlock;
	}
}
