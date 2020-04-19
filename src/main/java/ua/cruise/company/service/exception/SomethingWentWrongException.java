package ua.cruise.company.service.exception;

public class SomethingWentWrongException extends Exception{
    public SomethingWentWrongException(String message) {
        super(message);
    }

    public SomethingWentWrongException(String message, Throwable cause) {
        super(message, cause);
    }
}
