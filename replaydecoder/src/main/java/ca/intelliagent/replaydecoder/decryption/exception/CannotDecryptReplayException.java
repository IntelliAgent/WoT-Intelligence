package ca.intelliagent.replaydecoder.decryption.exception;

public class CannotDecryptReplayException extends RuntimeException {

    public CannotDecryptReplayException(String message, Exception e) {
        super(message, e);
    }
}


