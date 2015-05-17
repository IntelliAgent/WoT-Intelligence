package ca.unknown.replaydecoder.exception;

import java.io.IOException;

public class CannotReadSecondBlockException extends RuntimeException {
    public CannotReadSecondBlockException(String message, IOException e) {
        super(message, e);
    }
}
