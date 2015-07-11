package ca.intelliagent.replaydecoder.exception;

import java.io.IOException;

public class CannotReadThirdBlockException extends RuntimeException{
  public CannotReadThirdBlockException(String message, IOException e) {
    super(message, e);
  }
}
