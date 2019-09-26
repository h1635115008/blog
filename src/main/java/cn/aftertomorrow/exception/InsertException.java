package cn.aftertomorrow.exception;

import org.springframework.dao.DataAccessException;

public class InsertException extends DataAccessException {
    public InsertException(String message){
        super(message);
    }
}
