package com.projeto.upload.model;

import java.io.File;
import java.io.Serializable;

import org.primefaces.model.StreamedContent;

public class BannerFile implements Serializable{

	private static final long serialVersionUID = 1L;

	private File file;
	
	public BannerFile() {}
	
	public BannerFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
