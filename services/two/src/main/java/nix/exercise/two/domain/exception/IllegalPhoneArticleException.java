package nix.exercise.two.domain.exception;

public class IllegalPhoneArticleException extends RuntimeException {
    private static final String ERROR = "Order contains not valid phone";

    public IllegalPhoneArticleException() {
        super(ERROR);
    }
}
