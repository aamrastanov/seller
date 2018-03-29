package az.tezapp.seller.server.exception;

public enum ErrorCode {

    KEY_ACCESS_DENIED(10), ACCESS_DENIED(11), ILLEGAL_FIELD(12), OTHER_ERROR(99);

    private int value;

    private ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
