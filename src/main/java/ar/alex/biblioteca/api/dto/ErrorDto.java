package ar.alex.biblioteca.api.dto;

public class ErrorDto {

    private int code;
    private String mensaje;

    public ErrorDto(int code, String mensaje) {
        this.mensaje = mensaje;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
