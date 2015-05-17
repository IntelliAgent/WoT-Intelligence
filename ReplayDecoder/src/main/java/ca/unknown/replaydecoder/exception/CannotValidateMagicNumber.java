package ca.unknown.replaydecoder.exception;

public class CannotValidateMagicNumber extends RuntimeException {
    public CannotValidateMagicNumber(String message, Exception e) {
        super(message, e);
    }
}
