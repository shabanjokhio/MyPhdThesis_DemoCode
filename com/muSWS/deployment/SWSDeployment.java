package com.muSWS.deployment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SWSDeployment {
	//TODO: dynamically load WSMX home from properties file
	private static String WSMX_path = "D:\\WSMX_correctVersion\\wsmx-reshape1-Copy\\wsmx-dist\\build-CorrectVersion";
	private static File WSMX_loc= new File(WSMX_path);	
	public static File WSMX_resouces = new File (WSMX_loc, "resources\\resourcemanager");
	
	/*
	 * TODO: dynamically load from properties file
	 */
	public static File SWS_files= new File("D:\\SWS_Work");
	
	public boolean deploySWS(){		
		if (SWS_files.list().length==0)
			System.out.println("SWS_Work folder empty. Does not include files. Please specify at least SWS and Ontology files");
		else
			deploySWSs();
			deployOntologies();
			deployGoals();
			
			if (deploySWSs() && deployOntologies() && deployGoals())
				return true;
			else 
				return false;	
	}
	
	private boolean deploySWSs(){
		File source=null;
		File target= null;		
		try {
			String[] dirs= SWS_files.list();
			for (int i=0; i<dirs.length; i++){
				if (dirs[i].equals("SWSs")){
					 source = new File (SWS_files, dirs[i]);
					 System.out.println("source directory is\t"+source);					 
					 target=new File(WSMX_resouces, "\\SWS-Challenge\\ShipmentDiscovery\\SWSs");
					 copyDirectory(source, target);
				 }								
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}
	
	private boolean deployOntologies(){
		File source=null;
		File target= null;		
		try {
			String[] dirs= SWS_files.list();
			for (int i=0; i<dirs.length; i++){
				if (dirs[i].equals("Ontologies")){
					 source = new File (SWS_files, dirs[i]);
					 System.out.println("source directory is\t"+source);					 
					 target=new File(WSMX_resouces, "\\SWS-Challenge\\ShipmentDiscovery\\Ontologies");
					 copyDirectory(source, target);
				 }								
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
		return true;		
	}
	
	private boolean deployGoals() {		
		File source=null;
		File target= null;		
		try {
			String[] dirs= SWS_files.list();
			for (int i=0; i<dirs.length; i++){
				if (dirs[i].equals("Goals")){
					 source = new File (SWS_files, dirs[i]);
					 System.out.println("source directory is\t"+source);					 
					 target=new File(WSMX_resouces, "\\SWS-Challenge\\ShipmentDiscovery\\Goals");
					 copyDirectory(source, target);
				 }								
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
		return true;	
	}
	
	/*
	 * copy the source to the destination directory.
	 */
	public void copyDirectory(File sourceLocation , File targetLocation) throws IOException
     {          
			if (sourceLocation.isDirectory()) {
			    if (!targetLocation.exists()) {
			        targetLocation.mkdir();
			    }			    
			    String[] children = sourceLocation.list();
			    for (int i=0; i<children.length; i++) {
			        copyDirectory(new File(sourceLocation, children[i]),
			                new File(targetLocation, children[i]));
			    }
			} else {			    
			    InputStream in = new FileInputStream(sourceLocation);
			    OutputStream out = new FileOutputStream(targetLocation);			    
			    // Copy the bits from instream to outstream
			    byte[] buf = new byte[1024];
			    int len;
			    while ((len = in.read(buf)) > 0) {
			        out.write(buf, 0, len);
			    }
			    in.close();
			    out.close();
			}
    }	
		
	public static void main (String[] args){
		File f= new File(WSMX_path);
		System.out.println(f.getPath());
		
		
		System.out.println(WSMX_loc.getParent());
		String[] dirs= SWS_files.list();
		File source=null;
		for (int i=0; i<dirs.length;i++){
			System.out.println(dirs[i]);
			source = new File (SWS_files, dirs[i]);
		}	
		System.out.println(source.isDirectory());		
		System.out.println("Goals Copied\t"+new SWSDeployment().deployGoals());		
		System.out.println("Ontologies Copied\t"+new SWSDeployment().deployOntologies());
		System.out.println("SWSs Copied\t"+new SWSDeployment().deploySWSs());
	}
}
