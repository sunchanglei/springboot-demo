package com.boot.exception;

import com.boot.comm.ResponseMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public ResponseMap handlerResponse(Exception e){

        ResponseMap responseMap = new ResponseMap();

        responseMap.setFailReturn("");

        logger.error("",e);
        return responseMap;

    }
}
