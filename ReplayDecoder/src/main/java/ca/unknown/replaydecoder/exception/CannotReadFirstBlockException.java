package ca.unknown.replaydecoder.exception;

import java.io.IOException;

public class CannotReadFirstBlockException extends RuntimeException {
    public CannotReadFirstBlockException(String message, IOException e) {
        super(message, e);
    }
}
