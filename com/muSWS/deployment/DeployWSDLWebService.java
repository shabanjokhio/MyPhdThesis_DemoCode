package com.muSWS.deployment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.*;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.deployment.DeploymentEngine;
import org.apache.axis2.description.AxisServiceGroup;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import sun.misc.GC;

public class DeployWSDLWebService {	
	public static String muJava_Home= "C:\\muJavaTool";
	public static String WSWorkDIR_Str="C:\\AXIS2_WebServices\\HelloWS3\\generated3"; // new folder

	public static File WSWorkDIR_file=new File (WSWorkDIR_Str);

	//public static String WEBSERVER_Location="D:\\Tomcat-5.5.29";
	public static String AXIS2_location_Str="C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF\\services";

	//public static File web_server_path=new File(WEBSERVER_Location);
	public static File Axis2_location_path= new File(AXIS2_location_Str);

	public static void main(String[] args) throws IOException{				
		String file="HelloService2.aar";
		uploadWebService(file);
		
		ConfigurationContext cContext= ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF",
		"C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");
		
		AxisConfiguration axisConfig= cContext.getAxisConfiguration();
		
		java.util.Iterator<AxisServiceGroup> iterator= axisConfig.getServiceGroups();
		System.out.println("List of Web services deployed in the AXIS2 Tomcat");
		while(iterator.hasNext()){
			AxisServiceGroup g=iterator.next();
			System.out.println(g.getServiceGroupName());
		}
	}

	//private static File WS_workDir= new File (WSWorkDIR);	
	public static synchronized boolean generateDeployableWebService() throws FileNotFoundException{	
		boolean deployed=false;		
		//Deletes the existing ".aar" file in the lib directory: OR may be we should delete in the other location:		
		File wsFile = getRequiredFile (".aar", WSWorkDIR_file).get(0);
		if (wsFile.exists()){
			System.out.println("Jar exists.....deleting previous jar");
			if(wsFile.delete()){				
				wsFile=null;
				System.out.println("Deleted.");
			}else{
				System.out.println("Problem in deleting Jar file...");
			}				
			System.out.println("....creating new jar");
			executeAntBuild();
		}else{
			System.out.println("....No jar exists...Creating new jar");
			executeAntBuild();
		}
		File f= getRequiredFile (".aar", WSWorkDIR_file).get(0);
		if (f.exists())
			deployed=true;
		else 
			deployed=false;		
		//System.out.println("Killing the file handler...");
		f=null;
		return deployed;
	}

	public static synchronized boolean uploadWebService(String wsFileName) throws IOException{
		boolean uploaded=false;		 	 
		File wsAARFile= new File(Axis2_location_path, wsFileName);		 
		if (wsAARFile.exists()){
			System.out.println(".aar file exists on server!");	
			System.out.println("Deleting existing .aar file from server..");
			if( wsAARFile.delete()){				
				System.out.println("Deleted... and undeploying!");
				wsAARFile=null;
				uploaded = copyWSFileToAXISDir(wsFileName);
			}else{
				System.out.println("Problem in deleting previous .aar file from server...");
				uploaded = copyWSFileToAXISDir(wsFileName);
			}
			uploaded = copyWSFileToAXISDir(wsFileName);
			if(uploaded)
				System.out.println("Jar uploaded..!");
		} else{
			System.out.println("No Jar exists on server");
			System.out.println("Uploading the Jar for the first time...");
			uploaded=  copyWSFileToAXISDir(wsFileName);			 
		}	 
		File f= new File(Axis2_location_path, wsFileName);		 	
		if (f.exists())
			uploaded=true;
		else
			uploaded=false;
		//System.out.println("Killing the file handler...");		
		f=null;	
		return uploaded;		 	
	}
	//Programming logic to deploy web service using java code:
	public static synchronized void uploadWebService2(String wsFileName) throws AxisFault{
		System.out.println("Deploying web Service"+wsFileName+"In the Tomcat");
		
		ConfigurationContext cContext= ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF",
					"C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");
		File libDir= new File(WSWorkDIR_file, "build/lib");
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
		}
	}	
	
	/*
	public static synchronized void removeWebService(String wsFileName) throws AxisFault{
		ConfigurationContext cContext= ConfigurationContextFactory.createConfigurationContextFromFileSystem("C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF",
					"C:\\Tomcat-5.5.292\\webapps\\axis2\\WEB-INF\\conf\\axis2.xml");
		File libDir= new File(WSWorkDIR_file, "build/lib");
		File aarFile= new File (libDir, wsFileName);
		AxisServiceGroup axisServiceGroup= DeploymentEngine.loadServiceGroup(aarFile, cContext);
		AxisConfiguration axisConfig= cContext.getAxisConfiguration();
		axisConfig.addServiceGroup(axisServiceGroup); 	
	}*/
	
	private static synchronized boolean copyWSFileToAXISDir (String wsFileName){
		boolean copied=false;		   
		File buildFile= new File("build.xml"); // additional tasks build file in the project directory:		   
		Project p = new Project();		   
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());		   
		p.init();		   
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);		   
		helper.parse(p, buildFile);
		p.executeTarget(p.getDefaultTarget());	//copies the ".aar" file to the Jetty web server directory in the Temp folder.
		File f= new File (Axis2_location_path, wsFileName);
		if (f.exists())
			return copied=true;
		else copied=false;
		f=null;
		return copied;
	}	


	private static synchronized void executeAntBuild() {		   
		File buildFile= new File(DeployWSDLWebService.WSWorkDIR_file, "build.xml"); // the original build
		Project p = new Project();
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);
		helper.parse(p, buildFile);
		p.executeTarget(p.getDefaultTarget());		  
	}


	public static List<File> getRequiredFile(String fileNameOrSubstring, File searchDir) throws FileNotFoundException{
		//File[] reqFiles=null;
		List<File> reqFilesList= new ArrayList<File>();	

		List<File> allFiles= getFileListing(searchDir);
		for(File f: allFiles){	    	
			if(!f.getName().contains(fileNameOrSubstring))
				continue;	    		
			else 
				reqFilesList.add(f);
		}	
		return reqFilesList;		
	}


	/**
	 * Recursively walk a directory tree and return a List of all
	 * Files found; the List is sorted using File.compareTo().
	 *
	 * @param aStartingDir is a valid directory, which can be read.
	 */	  	
	public static List<File> getFileListing(File aStartingDir) throws FileNotFoundException {
		validateDirectory(aStartingDir);
		List<File> result = getFileListingNoSort(aStartingDir);
		Collections.sort(result);
		return result;
	}

	// PRIVATE //
	private static List<File> getFileListingNoSort(
			File aStartingDir
			) throws FileNotFoundException {
		List<File> result = new ArrayList<File>();
		File[] filesAndDirs = aStartingDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		for(File file : filesDirs) {
			result.add(file); //always add, even if directory
			if (!file.isFile()) {
				//must be a directory
				//recursive call!
				List<File> deeperList = getFileListingNoSort(file);
				result.addAll(deeperList);
			}
		}
		return result;
	}

	/**
	 * Directory is valid if it exists, does not represent a file, and can be read.
	 */
	static private void validateDirectory (File aDirectory) throws FileNotFoundException {
		if (aDirectory == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!aDirectory.exists()) {
			throw new FileNotFoundException("Directory does not exist: " + aDirectory);
		}
		if (!aDirectory.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: " + aDirectory);
		}
		if (!aDirectory.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: " + aDirectory);
		}
	}



	public static synchronized boolean replaceFileWith(File original, File mutant) throws FileNotFoundException{
		boolean replaced=false;
		if(!original.exists() || !mutant.exists()){
			System.out.println("Original or mutant file does not exist");
			return false;
		}
		FileInputStream fin=null;
		FileOutputStream fout=null;			
		System.out.println("Deleting original Jar File.."+original);
		if (original.delete()){
			System.out.println("deleted..");
			//original=null;
		}
		//File newOriginal= new File();
		
		try {
			original.createNewFile();
			fin= new FileInputStream(mutant);
			fout= new FileOutputStream(original);
			byte[] buf = new byte[1024];
			int len;
			while((len = fin.read(buf)) > 0){
				fout.write(buf, 0, len);
			}
			replaced=true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{		
			try {
				fin.close();			
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}			
		return replaced;	
	} 

	//Deletes all files and subdirectories under dir.
	//Returns true if all deletions were successful.
	//If a deletion fails, the method stops attempting to delete and returns false:
	//To be tested:
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}







}
