package com.banana.event.starter.base;

/**
 * event相关的异常
 * @author: banana
 * @date 2023-07-05 18:03
 */
public class EventException extends RuntimeException{

    /** */
    public EventException(String msg) {
        super(msg);
    }

    /** */
    private EventException(String msg, Throwable cause) {
        super(msg + " - "  + getMessage(cause), cause);
    }

    /** */
    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj instanceof Throwable ? ((Throwable)obj).getMessage() : obj.toString();
        }
    }

}
