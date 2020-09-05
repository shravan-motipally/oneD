package exception;

public class InputValidationException extends Exception {
    private final String message;

    public InputValidationException(String message)  {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}