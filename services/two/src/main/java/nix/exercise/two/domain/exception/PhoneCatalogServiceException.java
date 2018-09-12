package nix.exercise.two.domain.exception;

public class PhoneCatalogServiceException extends RuntimeException {
    public PhoneCatalogServiceException(final String message) {
        super(message);
    }
}
