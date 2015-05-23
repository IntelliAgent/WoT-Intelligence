package ca.unknown.replaydecoder.exception;

public class CannotValidateMagicNumberException extends RuntimeException {
    public CannotValidateMagicNumberException(String message, Exception e) {
        super(message, e);
    }
}
