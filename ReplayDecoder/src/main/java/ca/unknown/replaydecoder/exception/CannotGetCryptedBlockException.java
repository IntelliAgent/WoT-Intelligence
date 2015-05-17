package ca.unknown.replaydecoder.exception;

public class CannotGetCryptedBlockException extends RuntimeException {
    public CannotGetCryptedBlockException(String message, Exception e) {
        super(message, e);
    }
}
