package az.tezapp.seller.server.exception;

public class KeyAccessDeniedException extends ControllerLogicException {

    private static final long serialVersionUID = 8469344865982282268L;

    public KeyAccessDeniedException() {
        super(ErrorCode.KEY_ACCESS_DENIED, "Access denied");
    }

}
