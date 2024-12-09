package excepcions;

public class ErrorFitxerException extends Exception {
    public ErrorFitxerException(String missatge) {
        super(missatge);
    }
    public ErrorFitxerException(String missatge, Throwable causa) {
        super(missatge, causa);
    }

}