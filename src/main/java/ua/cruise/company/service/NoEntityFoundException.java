package ua.cruise.company.service;

public class NoEntityFoundException extends Exception{
    public NoEntityFoundException(String message) {
        super(message);
    }
}
