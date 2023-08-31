package ar.alex.biblioteca.business.exceptions;

public class BusinessException extends RuntimeException{
    // para que sea checked
    private int code;
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
