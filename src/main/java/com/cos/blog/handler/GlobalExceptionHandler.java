package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 예외상황이 생겼을 경우 해당 클래스로 들어옴
@RestController
public class GlobalExceptionHandler {

    // Exception으로 걸어두면 모든 Exception이 이 메소드로 들어온다.
    // IllegalArgumentException 발생했을 때 실행함
    @ExceptionHandler(value = IllegalArgumentException.class)
   public ResponseDto<String> handleArgumentException(IllegalArgumentException e){
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
