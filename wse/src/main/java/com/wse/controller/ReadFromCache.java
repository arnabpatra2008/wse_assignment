package com.wse.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.cacheonix.Cacheonix;
import org.cacheonix.cache.Cache;

public class ReadFromCache {
	
	
	public ReadFromCache(String pathName) throws IOException {
		
		
		 Cacheonix cacheonix = Cacheonix.getInstance();
	      Cache<String, TextFile> cache = cacheonix.getCache("application.file.cache");
	      File file = new File(pathName);

	      if (!file.exists()) {
	         // Invalidate cache
	         cache.remove(pathName);
	         // Return null (not found)

	         System.out.println("File Not Found");

	      }
	      // Get the file from the cache

	      TextFile textFile = cache.get(pathName);
	      // Check if the cached file exists

	      if (textFile == null) {
	         // Not found in the cache, put in the cache

	         textFile = readFile(file);
	         cache.put(pathName, textFile);
	      } else {
	         // Found in cache, check the modification time stamp
	         if (textFile.getLastModified() != file.lastModified()) {
	            // Update cache
	            textFile = readFile(file);
	            cache.put(pathName, textFile);
	         }

	      }


	      System.out.println("File Not Found");
	      System.out.println(textFile.getContent());
	      
		
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
