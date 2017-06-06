package az.tezapp.seller.server.exception;

public enum ErrorCode {	
	
	KeyAccessDenied(10),
	AccessDenied(11),
	IllegalField(12),
	OtherError(99);
	
	private int value;
	
	private ErrorCode(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
