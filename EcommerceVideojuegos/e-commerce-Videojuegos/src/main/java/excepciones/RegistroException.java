package excepciones;

/**
 *
 * @author petermoon
 */
public class RegistroException extends Exception{
    
    private final int codeError;

    public RegistroException(int codeError) {
        super();
        this.codeError = codeError;
    }

    public int getCodeError() {
        return codeError;
    }
    
}
