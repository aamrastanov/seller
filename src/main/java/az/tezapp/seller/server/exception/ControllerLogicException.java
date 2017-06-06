package az.tezapp.seller.server.exception;

public class ControllerLogicException extends Exception {

	private static final long serialVersionUID = 3110007695355755894L;

	protected ErrorCode errorCode;

	public ControllerLogicException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ControllerLogicException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode.getValue();
	}

}
