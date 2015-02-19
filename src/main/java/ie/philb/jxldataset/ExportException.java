/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.jxldataset;

/**
 *
 * @author pbradley
 */
public class ExportException extends Exception {

    private Throwable cause;

    public ExportException() {
        super();
    }

    public ExportException(String message) {
        super(message);
    }

    public ExportException(String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
    }

    public ExportException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public Throwable getLinkedCause() {
        return cause;
    }

}
