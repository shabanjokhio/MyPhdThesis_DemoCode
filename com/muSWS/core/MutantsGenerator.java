package com.muSWS.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.muSWS.deployment.DeployWSDLWebService;
import com.sun.tools.javac.comp.Env;

import java.io.*;
import java.util.*;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.wsmo.service.Choreography;

import org.wsmo.common.exception.InvalidModelException;
import org.wsmo.wsml.ParserException;

//import edu.emory.mathcs.backport.java.util.Arrays;
import mujava.*;

public class MutantsGenerator {
	
	public static String Mutants_LOC="C:\\muJavaTool\\result";
	public static File Mutant_LOC_path= new File(Mutants_LOC);
	public static File WSMX_rosourcesLOC= new File ("C:\\WSMX_correctVersion\\wsmx-reshape1-Copy\\wsmx-dist\\build-CorrectVersion\\resources\\resourcemanager");
	public static File SWSs_LOC= new File(WSMX_rosourcesLOC, "SWS-Challenge\\ShipmentDiscovery\\SWSs");
	public static String originalSWSName;
	public static File groundingLOC= new File("C:\\WSMX_correctVersion\\wsmx-reshape1-Copy\\wsmx-dist\\build-CorrectVersion\\resources\\grounding");

	public static Hashtable <File, String[]> filesAndLines= new Hashtable<File,String[]>();

	public static void main(String[] args) throws Exception{
		File originalFile= new File ("C:\\AXIS2_WebServices\\DiscountExample\\generated3\\src\\com\\discount\\DiscountWebServiceSkeleton.java");
		String swsIdentifier="http://www.wsmo.org/sws-challenge/discountWS#DiscountWS";
		File[] mutantSWSs= generateMutantSWSs(originalFile, swsIdentifier);
		for(int i=0; i<mutantSWSs.length;i++)
			System.out.println(mutantSWSs[i]);

		//File f= new File("D:\\AXIS2_WebServices\\HelloWS3\\generated3\\src\\com\\hello\\HelloResponse.java");
		/* Hashtable<File, String[]> fileAndLines=removeLinesContainingErrorsAndMakeNewFile(f);
        Enumeration <File> keys= fileAndLines.keys();		
		while(keys.hasMoreElements()){
			File f2= keys.nextElement();
			System.out.println("File from which lines are removed is\t"+f2);
			System.out.println("The lines removed are....");
			String[] lines=fileAndLines.get(f2);
			for(int i=0;i<lines.length;i++)
				System.out.println(lines[i]);

			System.out.println("Inserting the lines back......");			
			boolean inserted= InsertOriginalLineBackToFile(f2, fileAndLines.get(f2));			
			System.out.println("Lines inserted..."+inserted);			
		}		
		 */		

		/*Hashtable<File, String[]> linesFiles = makeFilesSuitableForMuJavaTool(f);

		Enumeration <File> keys= linesFiles.keys();		
		while(keys.hasMoreElements()){
			File f2= keys.nextElement();
			System.out.println("File from which lines are removed is\t"+f2);
			System.out.println("The lines removed are....");
			String[] lines=linesFiles.get(f2);
			for(int i=0;i<lines.length;i++)
				System.out.println(lines[i]);
		}

		compileSRCFilesOnly();
		 */
		//makeFilesSuitableForMuJavaTool()
		
		//System.out.println("Deleting original file");
		/*File f1= new File("D:\\AXIS2_WebServices\\HelloWS3\\generated3\\src\\com\\hello\\HelloResponse.java");
		if(f1.exists()){
			if(f1.delete())
				System.out.println("EXECUTED");
			else
				System.out.println("NOT EXECUTED");

		}*/
		//f.renameTo(new File("originalFile"));
		//System.out.println("Removed Line is\t"+commentOutTheBugyLine(f));
		//System.out.println("Removed Line is\t"+InsertOriginalLine(f, lineRemoved));

		/*String originalFile="D:\\AXIS2_WebServices\\HelloWS3\\generated2\\src\\com\\hello2\\HelloService2Skeleton.java";

		int beginIndex=originalFile.indexOf("\\", originalFile.indexOf("src"))+1;
		int endIndex=originalFile.indexOf(".java");
		String qClassName=originalFile.substring(beginIndex, endIndex);
		System.out.println(qClassName);

		String qClassNameProper= qClassName.replace("\\", ".");
		System.out.println(qClassNameProper);
		 */
		//public static File getRequiredSWSFileOnly(String originalWSId){
		/*	String mutantName="ROR_1";
		String swsId="http://www.wsmo.org/sws-challenge/WSHello#WSHello2";
		String originalWSName="WSHello2";

		String originalFile="HelloWS2.wsml";
		String originalFileNamewithoutExtenstion=(String)originalFile.subSequence(0, originalFile.indexOf("."));
		System.out.println(originalFileNamewithoutExtenstion);

		File mutantFile= makeMutantSWSFile(mutantName, swsId);
		String mutantName2="ROR_2";
		File mutantFile2= makeMutantSWSFile(mutantName2, swsId);

		String mutantName3="ROR_3";
		File mutantFile3= makeMutantSWSFile(mutantName3, swsId);


		File f= getOriginalSWSFile(swsId);
		System.out.println("original file is this.."+f);



		String line1= "webService WSHello2";
		String line2="webService WSHello2ROR_1";

		boolean original=false;
		String originalSWSName=MutantsGenerator.getSWSNameOnly(swsId);
		System.out.println(originalSWSName);

		if(line1.contains(originalSWSName)){
			String wsMutantOROriginal= line1.substring(line1.indexOf(originalSWSName), line1.length());
			System.out.println(wsMutantOROriginal);
			if(wsMutantOROriginal.equals(originalSWSName))
				original=true;
		}
		System.out.println("Line 1 is original\t"+original);
		original=false;
		if(line2.contains(originalSWSName)){
			String wsMutantOROriginal= line2.substring(line2.indexOf(originalSWSName), line2.length());
			System.out.println(wsMutantOROriginal);
			if(wsMutantOROriginal.equals(originalSWSName))
				original=true;
		}
		System.out.println("Line 2 is original\t"+original);
		 */


		/*String line= "<entry key=\"http://sws-challenge.org/shipper/v2/hello.wsdl \">hello.sawsdl</entry>";
		String gStr="hello.wsdl";

		String sawsdl=getSAWSDLFileNameFromGrounding(gStr);
		System.out.println("SAWSDL \t"+sawsdl);
		File sawsdLFile= getSAWSDLFile(sawsdl);

		System.out.println(sawsdLFile);

		String wsName = getWebServiceName(sawsdLFile);
		System.out.println("the Web service name is\t"+wsName);


		File swsWSML= new File(SWSs_LOC, "WSHello2.wsml");
		String gStr2= getDynamicBindingGroundingStr(swsWSML);
		System.out.println("Grounding String frm complete File is\t"+gStr2);*/


		//generateMutantSWSs("http://www.wsmo.org/sws-challenge/WSHello#WSHello2");

		//String originalFileName = "D:\\AXIS2_WebServices\\HelloWS3\\generated2\\src\\com\\hello2\\HelloService2Skeleton.java";
		//File originalFile= new File(originalFileName);

		//System.out.println(line1.substring(line1.indexOf("WSHello2"), line1.length()));
		//System.out.println(line2.substring(line2.indexOf("WSHello2"), line2.length()));

		//String originalWSName= line1.substring(line1.indexOf("WSHello2"), line1.length());
		//String mutantWSName=line2.substring(line2.indexOf("WSHello2"), line2.length());

		//System.out.println("original WS Name\t"+originalWSName);
		//System.out.println("mutant WS Name\t"+mutantWSName);



		/*String mutantName2="ROR_2";
		String swsId2="http://www.wsmo.org/sws-challenge/WSHello#WSHello2";
		String originalFile2="HelloWS2.wsml";
		String originalFileNamewithoutExtenstion2=(String)originalFile2.subSequence(0, originalFile2.indexOf("."));
		System.out.println(originalFileNamewithoutExtenstion2);


		File mutantFile2= makeMutantSWSFile(mutantName2,swsId2);
		 */



		/*File f= getRequiredSWSFileOnly("WSHello2");
	  		System.out.println("The file is\t"+f.getName());

		BufferedReader bf= new BufferedReader(new FileReader(f));
		StringBuffer strBf=new StringBuffer("");
		while(true){
			String line=bf.readLine();
			if(line!=null)
				strBf=strBf.append("\n").append(line);
			else
				break;
		}
		String str=strBf.toString();

		int index1=str.indexOf("withGrounding");
		int index2=str.indexOf("#", index1);
		String gStr= str.substring(index1, index2);
		System.out.println(gStr);
		System.out.println("Length of string is\t"+gStr.length());
		System.out.println("index of .\t"+gStr.indexOf("wsdl"));

		String s=groundingStr(gStr);
		System.out.println("The grounding file name is\t"+s);
		 */


		//System.out.println(index1);
		//System.out.println(index2);

		//System.out.println(strBf.substring(strBf.indexOf("withGrounding"), strBf.indexOf("#")));

		//org.wsmo.service.WebService ws= at.sti.wsmx.client.util.ParserAndSerializer.parseWebService(new StringReader(strBf.toString()));

		//System.out.println("WSML Variant is\t"+ws.getWsmlVariant());
		//System.out.println("Web Service Identifier\t"+ws.getIdentifier());
		//System.out.println("Web Service Capability\t"+ws.getCapability());

		//Set <org.wsmo.service.Interface> interfaces= ws.listInterfaces();

		//OutputStreamWriter os= new OutputStreamWriter(System.out);
		//os.flush();


		//System.out.println(line);
		//bf.close();


		/*try {
			dirs = getSWSDirsOnly(WSMX_rosourcesLOC);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 */
		//for(File f: dirs)
		//System.out.println("The directory is\t"+f);

		//DeployWSDLWebService.getRequiredFile("SWSs", searchDir)
		/*String file_name="com\\hello2\\HelloService2Skeleton.java";
		generateMutants(file_name);
		System.out.println("...Mutants Generated...");*/

		/*	String[] mutants= getMutantNames();
		System.out.println("Mutants=\t"+mutants.length);

		for(String m:mutants)
			System.out.println(m);
		 */
		//System.out.println("The SWS name for\t"+"http://www.wsmo.org/sws-challenge/WSHello#WSHello2\t is"+new MutantsGenerator().getSWSNameOnly("http://www.wsmo.org/sws-challenge/WSHello#WSHello2"));
		//System.out.println("The SWS name for\t"+"http://www.wsmo.org/sws-challenge/WSAmazon#AmazonWS2\t is"+new MutantsGenerator().getSWSNameOnly("http://www.wsmo.org/sws-challenge/WSAmazon#AmazonWS2"));
	}
	private static String groundingStr(String wholeStr){
		String s=null;
		s= wholeStr.substring(wholeStr.lastIndexOf("/")+1, wholeStr.length());
		return s;
	}

	public static String getDynamicBindingGroundingStr(File wsmlSWS){
		String gString=null;
		try {
			BufferedReader bf= new BufferedReader(new FileReader(wsmlSWS));
			String line="";
			while(true){
				line=bf.readLine();
				if(line!=null){
					if(line.contains("withGrounding")){
						String wholeStr=line.substring(line.indexOf("withGrounding"), line.lastIndexOf("#"));
						gString=wholeStr.substring(wholeStr.lastIndexOf("/")+1, wholeStr.length());
						break;
					}
				}else
					break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return gString;
	}
	static String getSAWSDLFileNameFromGrounding(String groundingString){
		String sawsdlFileName=null;
		try {
			BufferedReader bfr= new BufferedReader(new FileReader(new File(groundingLOC, "grounding.properties")));
			String line="";
			while(true){
				line=bfr.readLine();
				if(line!=null){
					if(line.contains(groundingString)){
						int beginIndex= line.indexOf(">", line.indexOf(groundingString));
						int endIndex = line.indexOf("<", line.indexOf(groundingString));
						sawsdlFileName= line.substring(beginIndex+1, endIndex);
					}
				}else
					break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return sawsdlFileName.trim();
	}

	 static File getSAWSDLFile (String sawsdlString){
		File sawsdl=null;
		try {
			sawsdl=DeployWSDLWebService.getRequiredFile(sawsdlString, groundingLOC).get(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sawsdl;
	}
	 static String getWebServiceName(File sawsdl_file){
		String wsName=null;
		try {
			BufferedReader bf=new BufferedReader(new FileReader(sawsdl_file));
			String line="";
			while(true){
				line=bf.readLine();
				if(line!=null){
					if(line.contains(":service") && !line.contains("/") ){ //its the start service tag:
						int beginIndex=line.indexOf("\"", line.indexOf("name"));// starts the value of the name attribute.
						int endIndex=line.lastIndexOf("\"");
						wsName=line.substring(beginIndex+1, endIndex);
					}
				}else
					break;
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return wsName;
	}

	static String getSWSIdentifier(File swsFile){
		String swsId=null;
		String swsNS=null, swsName=null;
		try{
			BufferedReader bf= new BufferedReader(new FileReader(swsFile));
			String line="";

			while(true){
				line=bf.readLine();
				if(line!=null){
					if (line.contains("namespace")){
						swsNS = line.substring(line.indexOf("\"")+1 , line.lastIndexOf("\""));
					}
					if (line.contains("webService")){
						swsName = line.substring("webService".length(), line.length()).trim();
					}
					swsId=swsNS+swsName;

				}else
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return swsId;
	}


	//Method to copy the src and classes directories to muJava Tool:
	static boolean copyToMuJava(){

		boolean copied=false;
		File muJavaHome=new File(System.getenv("MuJava_HOME"));
		
		
		File muSrc= new File(muJavaHome, "src");
		File muClasses= new File(muJavaHome, "classes");

		File buildFile= new File("build.xml"); // additional tasks: build file in the project directory:
		Project p = new Project();
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);
		helper.parse(p, buildFile);

		p.executeTarget("copySRC");
		p.executeTarget("copyClasses");

		if (muSrc.exists() && muClasses.exists())
			copied=true;
		else
			copied=false;

		return copied;
	}

	static void compileSRCFilesOnly(){
		File buildFile= new File("C:\\AXIS2_WebServices\\DiscountExample\\generated3\\build.xml");		
		Project p = new Project();
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);
		helper.parse(p, buildFile);
		p.executeTarget("compile.src");

	}

	private static boolean doesThisFileContainsErrorLineInIt(File file){
		boolean bugyFile=false;		
		String[] bugylines= new String[2];
		try{
			FileReader fr= new FileReader(file);
			BufferedReader bf= new BufferedReader(fr);
			String line="";
			while(true){
				line=bf.readLine();
				if(line!=null){
					if(line.contains("public interface"))	// if it is an interface
					{
						if(line.contains("extends org.apache.xmlbeans.XmlObject")) // also it extends this XmlObject interface
							bugyFile=true; // it will definetly have the two line of the statement
					}
				}else
					break;
			}
			fr.close();
			bf.close();	

		}catch(Exception e){
			e.printStackTrace();
		}
		return bugyFile;
	}
	private synchronized static String[] removeLinesContainingErrorsAndMakeNewFile(File file){ 		

		String[] linesRemoved=new String[2];
		String line1ToRemove=null;
		String line2ToRemove=null;
		String fileNameWithoutExtension=file.getName().substring(0, file.getName().indexOf(".java"));
		System.out.println(fileNameWithoutExtension);

		File tempFile = new File(file.getParent(), fileNameWithoutExtension+"2.java");
		try{
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			FileWriter fw= new FileWriter(tempFile);
			BufferedWriter bw=new BufferedWriter(fw);
			String line="";
			while(true){
				line=br.readLine();
				if(line!=null){
					if(line.contains("public static final org.apache.xmlbeans.SchemaType type")){
						line1ToRemove=line;
						line="";
						bw.write(line); // set the line to null;
						bw.flush();
						bw.newLine();
					}
					if (line.contains("org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(")){
						line2ToRemove=line;
						line="";
						bw.write(line); // set the line to null;
						bw.flush();
						bw.newLine();
					}
					bw.write(line); // set the line to null;
					bw.flush();
					bw.newLine();
				}
				else{
					break;
				}
			} //reading file completed...			
			linesRemoved[0]=line1ToRemove;
			linesRemoved[1]=line2ToRemove;
			fr.close();
			br.close();
			fw.close();
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(file.exists())
			if(file.delete());
				//System.out.println("Original file deleted....");
			else;
				//System.out.println("Not deleted...");

		//System.out.println("Renaming the temp file to original....");
		tempFile.renameTo(file);
		//System.out.println("Renamed....");		
		return linesRemoved;
	}

	static synchronized boolean InsertOriginalLineBackToFile(File file, String[] linesToInsert){ // denotes the original file name deteled and the lines deleted...
		boolean inserted=false;	

		String fileNameWithoutExtension=file.getName().substring(0, file.getName().indexOf(".java"));
		File tempFile = new File(file.getParent(), fileNameWithoutExtension+"2.java");
		try{
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			FileWriter fw= new FileWriter(tempFile);
			BufferedWriter bw=new BufferedWriter(fw);
			String line="";
			int i=0;
			while(true){
				line=br.readLine();
				if(line!=null){					
					if(line.contains("{")){						
						if(i<=0){ // if it is the first curley brace, only then insert the lines, on all others "{", do not do that.
							line=line+"\n"+linesToInsert[0]+"\n"+linesToInsert[1];						
							bw.write(line); // set the line to null;
							bw.flush();
							bw.newLine();
							inserted=true;
							++i; // increment the number of the curley braces opened!
							continue; // read the next line....
						}						
					} 					
					bw.write(line); // set the line to null;
					bw.flush();
					bw.newLine();					
				}
				else{
					break;
				}
			} //reading file completed...
			//System.out.println("read whole file");			
			fr.close();
			br.close();
			fw.close();
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(file.exists())
			if(file.delete());
				//System.out.println("Original file deleted....");
			else;
				//System.out.println("Not deleted...");

		//System.out.println("Renaming the temp file to original....");
		tempFile.renameTo(file);		
		//System.out.println("Renamed....");
		return inserted;
	}

	public static synchronized Hashtable<File, String[]> makeFilesSuitableForMuJavaTool(File originalFile){
		filesAndLines= new Hashtable<File,String[]>();
		File parentOfWSClass= originalFile.getParentFile();
		File[] filesAdjacentToWSClass= parentOfWSClass.listFiles();		
		for(int i=0;i<filesAdjacentToWSClass.length;i++){
			if(!filesAdjacentToWSClass[i].isDirectory()){
				if(doesThisFileContainsErrorLineInIt(filesAdjacentToWSClass[i])){										 
						String[] linesRemoved= removeLinesContainingErrorsAndMakeNewFile(filesAdjacentToWSClass[i]);
						filesAndLines.put(filesAdjacentToWSClass[i], linesRemoved);					
				}
			}
		}		
		return filesAndLines;
	}	
	//Tested after modification...
	public static File[] generateMutantSWSs(File originalFile, String originalSWSCompleteId){
		File[] mutantSWSFiles=null; // to be returned
		List<File> swsFilesList= new ArrayList<File> (); // helping to add files		
		try {	
			
			 compileSRCFilesOnly(); // //Step 1: compile all the files in the SRC folder of the working directory.
			 copyToMuJava(); //Step 2: copy the files from working directory, including the "src" directory, "classes" and "missingFiles" to MuJava folder.
			//Step 3: Preprocess for Generating Mutants.
			String originalFileName2=originalFile.toString();
			int beginIndex=originalFileName2.indexOf("\\", originalFileName2.indexOf("src"))+1;
			int endIndex=originalFileName2.indexOf(".java");
			String completeClassName=originalFileName2.substring(beginIndex, endIndex);
			String qName=completeClassName.replace("\\", ".");

			//Step 4: Generate Mutants by muJava Tool.
			//generateMutants(completeClassName+".java", qName); //I am having few error with this step, but I hope this will also be resolved inshaAllah!
			System.out.println("Mutants Generated Successfully..");

			//Step 5: For each mutant generated, make the SWS file, from the original one:
			String[] mutantNames=getMutantNames();
			mutantSWSFiles= new File[mutantNames.length];

			System.out.println("Total No. of mutants is\t"+mutantNames.length);
			for (int j=0; j<mutantNames.length;j++ ){
				//System.out.println("Making the WSML SWS for"+ mutantNames[j]);
				mutantSWSFiles[j]= makeMutantSWSFile(mutantNames[j], originalSWSCompleteId);
			}
			System.out.println("All the mutant SWS are generated..");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return mutantSWSFiles;
	}

	//Given the name of the mutant, e.g., ROR_1, and the complete identifier of the original SWS,
	//This method creates a new copy of the "originalSWS.wsml" file and saves it as the name of the mutant. E.g., "HelloSWS2ROR_1"
	public static File makeMutantSWSFile(String mutantName, String swsCompleteIdentifier) throws Exception {
		File mutantFile=null;
		File originalFile= getOriginalSWSFile(swsCompleteIdentifier);
		String originalFileStr=originalFile.getName();

		String originalFileNamewithoutExtenstion=originalFileStr.substring(0, originalFileStr.indexOf("."));
		String orginalWebServiceName=getSWSNameOnly(swsCompleteIdentifier);

		String mutantFileName=originalFileNamewithoutExtenstion+mutantName+".wsml";
		System.out.println("Mutant file is\t"+mutantFileName);

		mutantFile= new File(mutantFileName);

		if(!mutantFile.exists()){
			System.out.println("Mutant SWS file does not exist: Creating New File");
			mutantFile=new File(originalFile.getParentFile(), mutantFileName);
		}else if (mutantFile.exists()){
			System.out.println("Mutant SWS file already Exist. Deleting Existing One");
			if (mutantFile.delete())
				mutantFile=new File(mutantFileName);
		}

		BufferedReader bfr= new BufferedReader(new FileReader(originalFile));
		BufferedWriter bfw= new BufferedWriter(new FileWriter(mutantFile));

		String line="";
		while(true){
			line= bfr.readLine();
			if (line!=null){
				if(!line.contains(orginalWebServiceName)){
					bfw.write(line);
					bfw.newLine();
					bfw.flush();
					//System.out.println(line);
				}else if (line.contains(orginalWebServiceName)){
					String wsMutantOROriginal= line.substring(line.indexOf(orginalWebServiceName), line.length());
					if(wsMutantOROriginal.equals(orginalWebServiceName)){
						//System.out.println("Web service name found\t"+webServiceName);
						String mutantwsName=orginalWebServiceName+mutantName;
						line=line.replace(orginalWebServiceName, mutantwsName);
						bfw.write(line);
						bfw.newLine();
						bfw.flush();
					}
					//System.out.println(line);
				}
			}
			else
				break;
		}
		return mutantFile;
	}

	/* Returns the File (WSHello2.wsml), from the given complete Web service identifier:
	 * http://www.wsmo.org/sws-challenge/WSHello#WSHello2;	 */
	public static File getOriginalSWSFile(String completeSWSIdentifier){
		File requiredFile=null;
		String originalSWSName = getSWSNameOnly(completeSWSIdentifier);
		System.out.println("original Ws Name\t"+originalSWSName);
		try {
			File[] allSWSs= SWSs_LOC.listFiles();
			BufferedReader br= null;

			for (File f: allSWSs){
				br= new BufferedReader(new FileReader(f));
				String line="";
				while(line!=null){
					if(!line.contains(originalSWSName)){
						line = br.readLine();
					}
					else if (line.contains(originalSWSName)){ //True for both "HelloWS2" and also "Hello2ROR1" both
						String wsMutantOROriginal= line.substring(line.indexOf(originalSWSName), line.length());
						if (wsMutantOROriginal.equals(originalSWSName) )// True for only: "HelloWS2"
							requiredFile=f;
						break;
					}
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return requiredFile;
	}


	/*private static File[] getSWSDirsOnly (File originalDir) throws FileNotFoundException{
	File[] SWSDirs=null;
	List<File> SWSDirList= new ArrayList<File>();



	if(!originalDir.isDirectory()){
		System.out.println("Give a valid start Directory...");
		return SWSDirs;
	}
	else if (originalDir.isDirectory()) {
		SWSDirList= DeployWSDLWebService.getRequiredFile("SWSs", SWS_LOC);
	}

	Object[] SWSDirsObj= SWSDirList.toArray();
	SWSDirs= new File[SWSDirsObj.length];
	int i=0;
	for(Object o:SWSDirsObj){
		SWSDirs[i]=(File)o;
		i++;
	}
	return SWSDirs;
}*/


	private static String getSWSNameOnly(String completeIdentifier){
		return completeIdentifier.substring(completeIdentifier.indexOf("#")+1, completeIdentifier.length());
	}

	//returns the names of Mutants generated...
	public static String[] getMutantNames(){
		String[] mutant_names=null;
		int i=0;
		try {
			List<File> logFiles= DeployWSDLWebService.getRequiredFile("mutation_log", Mutant_LOC_path);
			File mutantslogFile= logFiles.get(0);
			FileInputStream fstream = new FileInputStream(mutantslogFile);
			//Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			//Hashtable<String, String> mutants= new Hashtable<String, String>();
			ArrayList<String> mutantsNamesList= new ArrayList<String>();
			String strLine= br.readLine();

			while(strLine!= null){
				String mutantName=strLine.substring(0, strLine.indexOf(':'));
				mutantsNamesList.add(mutantName);
				i++;
				strLine= br.readLine();
			}
			//Converting the mutants names LIST to String[]
			Object[] mutant_namesObjs=mutantsNamesList.toArray();
			mutant_names= new String[mutant_namesObjs.length];
			for(int ii=0; ii<mutant_names.length;ii++)
				mutant_names[ii]=(String)mutant_namesObjs[ii];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return mutant_names;
	}

	public static void generateMutants(String file_name, String qClassName){
		String[] traditional_ops = {"AORB", "AORS", "AOIU", "AOIS",  "AODS", "ROR", "COR", "COD", "COI", "SOR", "LOR", "LOI", "LOD", "ASRS" };

		try
		{
			MutationSystem.setJMutationStructure("C:\\muJavaTool");
			//MutationSystem.recordInheritanceRelation();
			setMutationSystemPathFor(file_name);
			MutationSystem.setJMutationPaths(qClassName);
			//MutationSystem.setJMutationPaths("com.simple.calc.impl.SimpleCalculator");

			System.out.println("Mutation System is set for original file");
			File original_file = new File(MutationSystem.SRC_PATH, file_name);
			System.out.println("original file path\t"+original_file.getAbsolutePath());


			TraditionalMutantsGenerator tmGenEngine = new TraditionalMutantsGenerator(original_file, traditional_ops);
			System.out.println("TraditionalMutantsGenerator object created Successfully...");
			tmGenEngine.makeMutants();
			System.out.println("Mutants Created...");
			tmGenEngine.compileMutants();
			System.out.println("Mutants Compiled...");
		}
		catch(OpenJavaException oje)
		{
			System.out.println("[OJException] " + file_name + " " + oje.toString());
		}
		catch(Exception exp)
		{
			System.out.println("[Exception] " + file_name + " " + exp.toString());
			exp.printStackTrace();
		}
		catch(Error er)
		{
			System.out.println("[Error] " + file_name + " " + er.toString());
		}
	}

	static void setMutationSystemPathFor(String file_name)
	{
		try
		{
			String temp = file_name.substring(0, file_name.length() - ".java".length());
			temp = temp.replace('/', '.');
			temp = temp.replace('\\', '.');
			int separator_index = temp.lastIndexOf(".");
			if(separator_index >= 0)
			{
				MutationSystem.CLASS_NAME = temp.substring(separator_index + 1, temp.length());
			} else
			{
				MutationSystem.CLASS_NAME = temp;
			}
			String mutant_dir_path = MutationSystem.MUTANT_HOME + "/" + temp;
			File mutant_path = new File(mutant_dir_path);
			mutant_path.mkdir();
			String class_mutant_dir_path = mutant_dir_path + "/" + MutationSystem.CM_DIR_NAME;
			File class_mutant_path = new File(class_mutant_dir_path);
			class_mutant_path.mkdir();
			String traditional_mutant_dir_path = mutant_dir_path + "/" + MutationSystem.TM_DIR_NAME;
			File traditional_mutant_path = new File(traditional_mutant_dir_path);
			traditional_mutant_path.mkdir();
			String original_dir_path = mutant_dir_path + "/" + MutationSystem.ORIGINAL_DIR_NAME;
			File original_path = new File(original_dir_path);
			original_path.mkdir();
			MutationSystem.CLASS_MUTANT_PATH = class_mutant_dir_path;
			MutationSystem.TRADITIONAL_MUTANT_PATH = traditional_mutant_dir_path;
			MutationSystem.ORIGINAL_PATH = original_dir_path;
			MutationSystem.DIR_NAME = temp;
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
}