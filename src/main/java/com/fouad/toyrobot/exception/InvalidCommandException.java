/**
 * 
 */
package com.fouad.toyrobot.exception;

/**
 * @author Ahmed
 *
 * Wrapper class for invalid commands exceptions
 */
public class InvalidCommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3385982926714998709L;

	public InvalidCommandException(String message){
		super(message);
	}
	
	public InvalidCommandException(String message, Throwable ex){
		super(message, ex);
	}
}
