package com.db2020prj.jwt;

import com.db2020prj.controllers.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidTokenHandler {
    @ExceptionHandler(value = {InvalidTokenException.class})
    @ResponseBody
    public Result<String> invalidTokenHandle(InvalidTokenException e) {
        return new Result(e.msg, e.code, e.msg);
    }
}
