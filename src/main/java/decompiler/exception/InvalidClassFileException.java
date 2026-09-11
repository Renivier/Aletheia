package decompiler.exception;

import java.io.IOException;

public class InvalidClassFileException extends IOException {
    public InvalidClassFileException(String message) {
        super(message);
    }
}
