package com.exception;

public class PersistenciaException extends Exception {
	
	public PersistenciaException(String s,Throwable t) {
		super(s,t);
	}

	public PersistenciaException(String s) {
		super(s);
	}

}
