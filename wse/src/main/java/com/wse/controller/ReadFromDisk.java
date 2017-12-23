package com.wse.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.cacheonix.Cacheonix;
import org.cacheonix.cache.Cache;

public class ReadFromDisk {
	
	public ReadFromDisk(String pathName) throws IOException {
		Cacheonix cacheonix = Cacheonix.getInstance();
	      Cache<String, TextFile> cache = cacheonix.getCache("application.file.cache");
	      
	      String fileContent=null;
	      
	      File file = new File(pathName);

	      if (!file.exists()) {
	         // Invalidate cache
	         cache.remove(pathName);
	         // Return null (not found)

	         System.out.println("No File Found of file:");

	      }
	      // Get the file from the cache

	      TextFile textFile = cache.get(pathName);
	      // Check if the cached file exists

	      if (textFile == null) {
	         // Not found in the cache, then read from disk

	    	FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			
			
			System.out.println("Contents of file:");
			System.out.println(stringBuffer.toString());
			
			fileContent = stringBuffer.toString();
			
			System.out.println(fileContent);
			
	        
	      } else {
	         // Found in cache, check the modification time stamp
	         if (textFile.getLastModified() != file.lastModified()) {
	            // Update cache
	            textFile = readFile(file);
	            cache.put(pathName, textFile);
	         }
	         
	         System.out.println("Contents of file:");
	         System.out.println( textFile.getContent());

	      }
	}
	
	
	
		
	
	private static TextFile readFile(File file) throws IOException {

	      char[] buffer = new char[1000];

	      FileReader fileReader = new FileReader(file);

	      StringBuilder fileContent = new StringBuilder((int) file.length());
	      for (int bytesRead = fileReader.read(buffer); bytesRead != -1; ) {
	         fileContent.append(buffer, 0, bytesRead);
	      }
	      fileReader.close();

	      TextFile textFile = new TextFile();
	      textFile.setContent(fileContent.toString());
	      textFile.setLastModified(file.lastModified());
	      return textFile;

	   }

}
