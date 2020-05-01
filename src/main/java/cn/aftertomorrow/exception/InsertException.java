package cn.aftertomorrow.exception;

import cn.aftertomorrow.controller.ArticleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

public class InsertException extends DataAccessException {
    private static final Logger logger = LoggerFactory.getLogger(InsertException.class);

    public InsertException(String message) {
        super(message);

    }
}
