package com.wse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.cacheonix.Cacheonix;
import org.cacheonix.cache.Cache;

import com.wse.controller.ReadFromCache;
import com.wse.controller.ReadFromDisk;
import com.wse.controller.TextFile;

/**
 * Hello world!
 *
 */
public class MainApp 
{
   
    	public static void main(String[] args) throws IOException {



    	      // Replace the file name with an actual file name
    		 String pathName = "Path of TXT file to be put here";

    	     // String pathName = "Path of TXT file to be put here";



    	      MainApp fileCache= new MainApp();
    	      Cacheonix cacheonix = Cacheonix.getInstance();
    	      Cache<String, TextFile> cache = cacheonix.getCache("application.file.cache");
    	      File file = new File(pathName);

    	      TextFile textFile = cache.get(pathName);
    	      // Check if the cached file exists

    	      if (textFile == null) {
    	        new ReadFromDisk(pathName);
    	      } else {
    	         new ReadFromCache(pathName);

    	      }



    	      

    	     

    	   
    }
    	
    	
    	
    	
    	
    	
    	  

    	}