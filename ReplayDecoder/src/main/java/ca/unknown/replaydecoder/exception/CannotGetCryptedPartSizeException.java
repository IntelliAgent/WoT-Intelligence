package ca.unknown.replaydecoder.exception;

import java.io.IOException;

public class CannotGetCryptedPartSizeException extends RuntimeException {
    public CannotGetCryptedPartSizeException(String message, IOException e) {
        super(message, e);
    }
}
