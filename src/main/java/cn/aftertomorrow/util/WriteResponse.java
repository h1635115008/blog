package cn.aftertomorrow.util;

public class WriteResponse {
    private Integer status;
    private String message;

    public WriteResponse() {
        status = 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
