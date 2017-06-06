package az.tezapp.seller.server.controller;

public class LogicErrorResponse {

	private int errorCode;
	
	private String message;
	
	public LogicErrorResponse() {
		
	}

	public LogicErrorResponse(int errorCode, String message) {		
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getMessage() {
		return message;
	}
	
}
