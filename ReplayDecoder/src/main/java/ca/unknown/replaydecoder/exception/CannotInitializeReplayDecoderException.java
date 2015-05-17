package ca.unknown.replaydecoder.exception;

public class CannotInitializeReplayDecoderException extends RuntimeException {
    public CannotInitializeReplayDecoderException(String message, Exception e) {
        super(message, e);
    }
}
