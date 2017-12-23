package com.wse.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateAndWriteFileIn {
	
public CreateAndWriteFileIn( String filePath ) {
	try {

	      File file = new File(filePath);

	      if (file.exists()){
	    	  System.out.println("File already exists.");
	        
	      }else{
	    	  
	    	  file.createNewFile();
	    	  System.out.println("File is created!");
	        
	      }
	      
	      

  	} catch (IOException e) {
	      e.printStackTrace();
	}
}


public CreateAndWriteFileIn( String filePath, String content ) {
	try {

	      File file = new File(filePath);

	      if (file.exists()){
	    	  System.out.println("File already exists.");
	        
	      }else{
	    	  
	    	  file.createNewFile();
	    	  System.out.println("File is created!");
	        
	      }
	      
	      FileWriter writer = new FileWriter(file);
	      writer.write(content);
	      writer.close();

  	} catch (IOException e) {
	      e.printStackTrace();
	}
}




}
