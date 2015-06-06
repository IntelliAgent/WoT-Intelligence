package ca.intelliagent.replaydecoder.exception;

public class CannotValidateMagicNumberException extends RuntimeException {
    public CannotValidateMagicNumberException(String message, Exception e) {
        super(message, e);
    }
}
