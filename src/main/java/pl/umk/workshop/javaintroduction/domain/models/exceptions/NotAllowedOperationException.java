package pl.umk.workshop.javaintroduction.domain.models.exceptions;

public class NotAllowedOperationException extends RuntimeException {
    public NotAllowedOperationException() {
        super("Could not process operation, because of insufficient permissions");
    }
}
