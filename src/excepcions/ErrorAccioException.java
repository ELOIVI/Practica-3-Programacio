package excepcions;

public class ErrorAccioException extends Exception {
    public ErrorAccioException(String missatge) {
        super(missatge);
    }

    public ErrorAccioException(String missatge, Throwable causa) {
        super(missatge, causa);
    }
}    

