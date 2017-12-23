package com.wse.controller;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TextFile implements Externalizable{
	
	private long lastModified;
	private String content;
	private long size;
	
	public TextFile() {



	   }

	public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
		lastModified = objectInput.readLong();
	         content = objectInput.readUTF();
	         size = objectInput.readByte();
		
	}

	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(lastModified);
		objectOutput.writeUTF(content);
		
	}
	
	public long getLastModified() {
	      return lastModified;
	   }
	
	public String getContent() {
	      return content;
	   }
	
	public void setContent(final String content) {
	      this.content = content;
	   }
	
	public void setLastModified(final long lastModified) {
	      this.lastModified = lastModified;

	   }
	
	
	public String toString() {
	      return "TextFile{" + "lastModified=" + lastModified + ", content='" + content + '\'' + '}';

	   }
	
	 

}
