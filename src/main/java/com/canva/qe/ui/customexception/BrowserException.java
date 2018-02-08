package com.canva.qe.ui.customexception;

public class BrowserException extends Exception {

	public BrowserException(){
		super("Browser exception! Browser parameter should be either 'chrome' or 'firefox'");
	}
	
	public BrowserException(String errorMessage){
		super(errorMessage);
	}
}
