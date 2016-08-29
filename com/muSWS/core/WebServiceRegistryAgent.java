package com.muSWS.core;

//import java.io.IOException;
import java.rmi.RemoteException;
//import java.util.HashSet;
//import java.util.Set;

import org.wsmo.execution.commons.nonwsmo.ServiceRegistryEntry;
import org.wsmo.execution.commons.nonwsmo.ResourceEntry;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axiom.soap.impl.llom.soap12.SOAP12Factory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
//import org.wsmo.common.exception.InvalidModelException;
//import org.wsmo.wsml.ParserException;

import at.sti.wsmx.client.entrypoint.ExceptionException0;
import at.sti.wsmx.client.registry.WSMXRegistryServiceStub;
import at.sti.wsmx.client.registry.WSMXRegistryServiceStub.AddServiceRegistryEntry;
//import at.sti.wsmx.client.registry.WSMXRegistryServiceStub.ServiceRegistryEntry;
//import at.sti.wsmx.client.registry.WSMXRegistryServiceStub.ResourceEntry;
import at.sti.wsmx.client.registry.WSMXRegistryServiceStub.GetServiceRegistryEntry;
import at.sti.wsmx.client.registry.WSMXRegistryServiceStub.ModifyServiceRegistryEntry;
import at.sti.wsmx.client.registry.WSMXRegistryServiceStub.RemoveServiceRegistryEntry;

public class WebServiceRegistryAgent extends at.sti.wsmx.client.WSMXServiceRegistryClient{
	
		
	public WebServiceRegistryAgent(String endpoint, String user, String password) throws AxisFault {
		super(endpoint, user, password);		
	}	
	private static  WSMXRegistryServiceStub registryStub=null;
	
	
	public static void main(String[] args){
		WebServiceRegistryAgent agent=null;
		try {
			agent = new WebServiceRegistryAgent("http://localhost:8050/axis/services/WSMXRegistryService","Admin","Admin");
		} catch (AxisFault e1) {			
			e1.printStackTrace();
		}
		String resourceId="HelloService2";
		String resourcePath="D:\\AXIS2_WebServices\\HelloWS3\\generated3\\build\\lib\\HelloService2.aar";
		ResourceEntry[] resources = {new ResourceEntry(resourceId, resourcePath)};
		
		ServiceRegistryEntry param= new ServiceRegistryEntry(ServiceRegistryEntry.WSDL_TYPE);
		param.setResources(resources);
		param.setDescription("Hello Web Service");
		param.setEndPoints(new String[]{"http://localhost:8050/axis/services/HelloService2"});
		param.setIdentifier("HelloWebService");
		param.setType(ServiceRegistryEntry.WSDL_TYPE);
		
		boolean uploaded=false;
		try {
			uploaded = agent.addServiceRegistryEntry(param);
		} catch (RemoteException e) {			
			e.printStackTrace();
		}
		System.out.println("HelloService uploaded\t"+uploaded);		
	}
	
	/*public synchronized boolean addServiceRegistry(String resourceId, String resourcePath){		
		boolean serviceRegistered=false;
		try {			
			registryStub= new WSMXRegistryServiceStub("http://localhost:8050/axis/services/WSMXRegistryService");
			//at.sti.wsmx.client.WSMXServiceRegistryClient registryClient= new at.sti.wsmx.client.WSMXServiceRegistryClient("http://localhost:8050/axis/services/WSMXRegistryService", "Admin", "Admin");
			
			ServiceClient sc= registryStub._getServiceClient(); //Get service client, the easiest way to call service.				
			sc.addHeader(createAuthenticationHeaderBlock());
			//Response of Add Service
			WSMXRegistryServiceStub.AddServiceRegistryEntryResponse addServiceEntryResponse= null;
			
			WSMXRegistryServiceStub.AddServiceRegistryEntry addServiceEntryRequest =  new AddServiceRegistryEntry();  //upper most type.			
			WSMXRegistryServiceStub.ServiceRegistryEntry serviceEntry= new WSMXRegistryServiceStub.ServiceRegistryEntry();		
			ResourceEntry resource= new ResourceEntry();			
			resource.setIdentifier(resourceId);
			resource.setResource(resourcePath);			
			serviceEntry.addResources(resource);
			
			addServiceEntryRequest.setEntry(serviceEntry);	
			
			addServiceEntryResponse= registryStub.addServiceRegistryEntry(addServiceEntryRequest);
			registryStub.addServiceRegistryEntry(addServiceEntryRequest);
			
			serviceRegistered= addServiceEntryResponse.get_return();
		} catch (RemoteException e) {			
			e.printStackTrace();
		}		 		
		return serviceRegistered;		
	}*/
	
	
	
	/*public synchronized boolean removeServiceRegistry(){
		
		
		
	}
	*/
	
	
	
	
	
	
	
	// Create the authentication header block:
	private SOAPHeaderBlock createAuthenticationHeaderBlock() {
		SOAP12Factory factory = (SOAP12Factory) OMAbstractFactory.getSOAP12Factory();

		OMNamespace omNs = factory.createOMNamespace("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","wsse");

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
